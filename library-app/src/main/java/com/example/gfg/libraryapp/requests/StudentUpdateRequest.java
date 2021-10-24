package com.example.gfg.libraryapp.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentUpdateRequest {

    private int age;
    private String name;
    private String email;

}
