package com.example.gfg.libraryapp.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookReturnRequest {

    private int studentId;
    private int bookId;
    private String remarks;

}
