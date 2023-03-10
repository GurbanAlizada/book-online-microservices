package com.example.libaryservice.service;

import com.example.libaryservice.dto.response.LibraryDto;
import com.example.libaryservice.exception.LibraryNotFoundException;
import com.example.libaryservice.model.Library;
import com.example.libaryservice.repository.LibraryRepository;
import org.springframework.stereotype.Service;


@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public LibraryDto getAllBooksInLibraryById(String id){
        Library libary = getById(id);
        LibraryDto result = new LibraryDto(libary.getId());
        return result;
    }


    public LibraryDto createLibrary(){
        Library libary = libraryRepository.save(new Library());
        return new LibraryDto(libary.getId());
    }


    protected Library getById(String id){
        Library libary = libraryRepository.findById(id)
                .orElseThrow(
                        () -> new LibraryNotFoundException("libary could not found: " + id)
                );
        return libary;
    }


}
