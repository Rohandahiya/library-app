package com.example.gfg.libraryapp.repository;

import com.example.gfg.libraryapp.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

//    @Query("select a from Author where a.email = ?1 ")
//    Author getAuthor(String email);

    Author findByEmail(String email);       // We can use findByEmail so that hibernate automatically finds based on email no need to write seperate query
}
