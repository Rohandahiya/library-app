package com.example.gfg.libraryapp.services;

import com.example.gfg.libraryapp.models.Author;
import com.example.gfg.libraryapp.models.Book;
import com.example.gfg.libraryapp.repository.BookRepository;
import com.example.gfg.libraryapp.requests.BookCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    public void createBook(BookCreateRequest bookCreateRequest) {



        Author author = authorService.getAuthorByEmail(bookCreateRequest.getAuthor().getEmail());

        Book book = Book.builder()
                .name(bookCreateRequest.getName())
                .cost(bookCreateRequest.getCost())
                .bookCategory(bookCreateRequest.getBookCategory())
                .author(author)
                .build();

        if(author==null){
            author = Author.builder()
                    .name(bookCreateRequest.getAuthor().getName())
                    .email(bookCreateRequest.getAuthor().getEmail())
                    .country(bookCreateRequest.getAuthor().getCountry())
                    .age(bookCreateRequest.getAuthor().getAge())
                    .books(Arrays.asList(book))
                    .build();

           author = authorService.createAuthor(author);
        }else{
            author.getBooks().add(book);
            author.setBooks(author.getBooks());
            authorService.createAuthor(author); // updating the author table
        }

        book.setAuthor(author);
        bookRepository.save(book);

    }

    public Book getBook(int id){
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public void createBook(Book book){
        bookRepository.save(book);
    }
}
