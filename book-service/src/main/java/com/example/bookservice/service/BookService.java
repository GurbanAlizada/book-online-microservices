package com.example.bookservice.service;


import com.example.bookservice.dto.BookDto;
import com.example.bookservice.model.Book;
import com.example.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<BookDto> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        List<BookDto> result = books
                .stream()
              //.map(n -> BookDto.convert(n))
                .map(BookDto::convert)
                .collect(Collectors.toList());
        return result;
    }


}
