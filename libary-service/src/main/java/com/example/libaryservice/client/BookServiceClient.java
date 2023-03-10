package com.example.libaryservice.client;

import com.example.libaryservice.dto.response.BookDto;
import com.example.libaryservice.dto.response.BookIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "book-service" , path = "/v1/book")
public interface BookServiceClient {

    @GetMapping
    ResponseEntity<List<BookDto>> getAllBooks();

    @GetMapping("/isbn/{isbn}")
    ResponseEntity<BookIdDto> getBooksByIsbn(@PathVariable String isbn);

    @GetMapping("/{id}")
    ResponseEntity<BookDto> getBookById(@PathVariable String id);



}
