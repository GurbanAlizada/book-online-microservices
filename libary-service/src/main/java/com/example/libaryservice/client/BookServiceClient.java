package com.example.libaryservice.client;

import com.example.libaryservice.dto.response.BookDto;
import com.example.libaryservice.dto.response.BookIdDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "book-service" , path = "/v1/book")
public interface BookServiceClient {

    Logger logger = LoggerFactory.getLogger(BookServiceClient.class);

    @GetMapping
    ResponseEntity<List<BookDto>> getAllBooks();

    @GetMapping("/isbn/{isbn}")
    //@CircuitBreaker(name = "getBooksByIsbnCircuitBreaker" , fallbackMethod = "getBookFallback")
    ResponseEntity<BookIdDto> getBooksByIsbn(@PathVariable(value = "isbn") String isbn);

//    default ResponseEntity<BookIdDto> getBookFallback(String isbn , Exception exception){
//        logger.info("Book not found by isbn : " + isbn + "returning default BookDto object");
//        return  ResponseEntity.ok(new BookIdDto("default-book-id" , "defult-book-isbn"));
//    }



    @GetMapping("/{id}")
    //@CircuitBreaker(name = "getBooksByIdCircuitBreaker" , fallbackMethod = "getBookByIdFallback")
    ResponseEntity<BookDto> getBookById(@PathVariable(value = "id") String id);

//    default ResponseEntity<BookDto> getBookByIdFallback(String id , Exception exception){
//        logger.info("Book not found by id : " + id + "returning default BookDto object");
//        return  ResponseEntity.ok(new BookDto(new BookIdDto("default-book-id" , "defult-book-isbn")));
//    }


}
