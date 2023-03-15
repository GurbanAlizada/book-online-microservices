package com.example.libaryservice.client;

import com.example.libaryservice.exception.BookNotFoundException;
import com.example.libaryservice.exception.ExceptionMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class RetrieveMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {

        ExceptionMessage exceptionMessage = null;

        try(InputStream bodyIs = response.body().asInputStream()) {

            exceptionMessage = new ExceptionMessage(
                    (String) response.headers().get("date").toArray()[0] ,
                    response.status(),
                    HttpStatus.resolve(response.status()).getReasonPhrase(),
                    IOUtils.toString(bodyIs , StandardCharsets.UTF_8),
                    response.request().url()
                    );

        } catch (IOException e) {
            return new Exception(e.getMessage());
        }

        switch (response.status()){
            case 404:
                return new BookNotFoundException(exceptionMessage);
            default :
               return errorDecoder.decode(s,response);
        }

    }


}
