package com.example.libaryservice.repository;

import com.example.libaryservice.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, String> {


}
