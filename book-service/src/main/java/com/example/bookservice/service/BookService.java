package com.example.bookservice.service;


import com.example.bookservice.dto.BookDto;
import com.example.bookservice.dto.BookIdDto;
import com.example.bookservice.exception.BookNotFoundException;
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


    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> result = books
                .stream()
                //.map(n -> BookDto.convert(n))
                .map(BookDto::convert)
                .collect(Collectors.toList());
        return result;
    }

    public BookIdDto findByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException("Book could not found by isbn " + isbn));
        BookIdDto result = BookIdDto.convert(book.getId(), book.getTitle());
        return result;
    }


    public BookDto findBookById(String id){
        Book fromDb = findById(id);
        BookDto result = BookDto.convert(fromDb);
        return result;
    }


    protected Book findById(String id){
        Book book = bookRepository.findById(id)
                .orElseThrow(
                        () -> new BookNotFoundException("Book could not found: " + id)
                );
        return book;
    }





}
