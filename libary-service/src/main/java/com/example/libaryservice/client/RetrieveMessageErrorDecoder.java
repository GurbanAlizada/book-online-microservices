package com.example.libaryservice.client;

import com.example.libaryservice.exception.BookNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {

        ExceptionMessage exceptionMessage = null;

        try(InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readValue(bodyIs , ExceptionMessage.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }

        switch (response.status()){
            case 404:
                return new BookNotFoundException(exceptionMessage.message() != null ? exceptionMessage.message() : "Not found");
            default :
               return errorDecoder.decode(s,response);
        }

    }


}
