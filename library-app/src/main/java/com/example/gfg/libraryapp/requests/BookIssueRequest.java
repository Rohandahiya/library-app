package com.example.gfg.libraryapp.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookIssueRequest {

    private int studentId;
    private int bookId;

}
