package com.example.bookservice.controller;


import com.example.bookservice.dto.request.CreateBookRequest;
import com.example.bookservice.dto.response.BookDto;
import com.example.bookservice.dto.response.BookIdDto;
import com.example.bookservice.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/book")
public class BookController {

    private final BookService bookService;
    Logger logger = LoggerFactory.getLogger(BookController.class);


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookIdDto> getBooksByIsbn(@PathVariable String isbn){
        return ResponseEntity.ok(bookService.findByIsbn(isbn));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable String id){
        logger.info("return book by id " + id);
        return ResponseEntity.ok(bookService.findBookById(id));
    }


    @PostMapping
    public ResponseEntity<BookIdDto> createBook(@RequestBody @Valid CreateBookRequest request){
        return ResponseEntity.ok(bookService.createBook(request));
    }



}
