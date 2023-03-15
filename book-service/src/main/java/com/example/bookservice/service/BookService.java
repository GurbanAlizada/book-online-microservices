package com.example.bookservice.service;


import com.example.bookservice.dto.request.CreateBookRequest;
import com.example.bookservice.dto.response.BookDto;
import com.example.bookservice.dto.response.BookIdDto;
import com.example.bookservice.exception.BookNotFoundException;
import com.example.bookservice.model.Book;
import com.example.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        final List<BookDto> result = books
                .stream()
                //.map(n -> BookDto.convert(n))
                .map(BookDto::convert)
                .collect(Collectors.toList());
        return result;
    }

    public BookIdDto findByIsbn(String isbn) {
         Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(
                        () -> new BookNotFoundException("Book could not found by isbn " + isbn)
                );
        final BookIdDto result = BookIdDto.convert(book.getId(), book.getTitle());
        return result;
    }


    public BookDto findBookById(String id){
        Book fromDb = findById(id);
        final BookDto result = BookDto.convert(fromDb);
        return result;
    }


    @Transactional
    public BookIdDto createBook(CreateBookRequest request){
        Book book = new Book(
                request.getTitle() ,
                request.getAuthor() ,
                request.getIsbn() ,
                request.getPressName() ,
                request.getPublishYear()
        );
        final Book fromDb = bookRepository.save(book);
        final BookIdDto result = BookIdDto.convert(fromDb.getId(), fromDb.getIsbn());
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
