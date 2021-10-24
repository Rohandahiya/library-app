package com.example.gfg.libraryapp.requests;

import com.example.gfg.libraryapp.models.Author;
import com.example.gfg.libraryapp.models.BookCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateRequest {

    private int cost;
    private String name;
    private BookCategory bookCategory;
    private Author author;
}
