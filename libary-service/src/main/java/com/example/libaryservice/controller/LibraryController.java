package com.example.libaryservice.controller;


import com.example.libaryservice.dto.request.AddBookRequest;
import com.example.libaryservice.dto.response.LibraryDto;
import com.example.libaryservice.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {

    Logger logger = LoggerFactory.getLogger(LibraryController.class);
    private final LibraryService libraryService;
    private final Environment environment;

    public LibraryController(LibraryService libraryService, Environment environment) {
        this.libraryService = libraryService;
        this.environment = environment;
    }


    @GetMapping("/{id}")
    public ResponseEntity<LibraryDto> getAllBookInLibraryById(@PathVariable String id){
        return ResponseEntity.ok(libraryService.getAllBooksInLibraryById(id));
    }


    @PostMapping
    public ResponseEntity<LibraryDto> createLibrary(){
        logger.info("Library created on port number : " +  environment.getProperty("local.server.port"));
        return ResponseEntity.ok(libraryService.createLibrary());
    }

    @PutMapping
    public ResponseEntity<Void> addBookToLibrary(@RequestBody @Valid AddBookRequest request){
        libraryService.addBookToLibrary(request);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<List<String>> getAllLabraries(){
        return ResponseEntity.ok(libraryService.getAllLibraries());
    }


}
