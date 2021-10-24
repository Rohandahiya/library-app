package com.example.gfg.libraryapp.controller;

import com.example.gfg.libraryapp.models.Book;
import com.example.gfg.libraryapp.requests.BookCreateRequest;
import com.example.gfg.libraryapp.services.BookService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable("id") int id){
        return bookService.getBook(id);
    }

    @JsonIgnore
    @GetMapping("/books/all")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }


    @PostMapping("/book")
    public void createbook(@RequestBody BookCreateRequest bookCreateRequest){
        bookService.createBook(bookCreateRequest);
    }



}
