package com.example.libaryservice.service;

import com.example.libaryservice.client.BookServiceClient;
import com.example.libaryservice.dto.request.AddBookRequest;
import com.example.libaryservice.dto.response.LibraryDto;
import com.example.libaryservice.exception.LibraryNotFoundException;
import com.example.libaryservice.model.Library;
import com.example.libaryservice.repository.LibraryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;

    public LibraryService(LibraryRepository libraryRepository, BookServiceClient bookServiceClient) {
        this.libraryRepository = libraryRepository;
        this.bookServiceClient = bookServiceClient;
    }

    public LibraryDto getAllBooksInLibraryById(String id){
        Library libary = getById(id);
        LibraryDto result = new LibraryDto(libary.getId() ,
                libary.getUserBook()
                        .stream()
                        .map(bookServiceClient::getBookById)
                        .map(ResponseEntity::getBody)
                        .collect(Collectors.toList())

        );
        return result;
    }


    @Transactional
    public LibraryDto createLibrary(){
        Library libary = libraryRepository.save(new Library());
        return new LibraryDto(libary.getId());
    }


    @Transactional
    public void addBookToLibrary(AddBookRequest request){
        String bookId = bookServiceClient.getBooksByIsbn(request.getIsbn()).getBody().getId();
        Library library = getById(request.getId());
        library.getUserBook().add(bookId);
        libraryRepository.save(library);
    }



    protected Library getById(String id){
        Library libary = libraryRepository.findById(id)
                .orElseThrow(
                        () -> new LibraryNotFoundException("libary could not found: " + id)
                );
        return libary;
    }


    public List<String> getAllLibraries() {
        List<Library> libraries = libraryRepository.findAll();
        List<String> libraryIds = libraries.stream().map(n->n.getId()).collect(Collectors.toList());
        return libraryIds;
    }


}
