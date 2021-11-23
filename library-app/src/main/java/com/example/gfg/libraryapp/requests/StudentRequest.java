package com.example.gfg.libraryapp.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequest {

    private String email;
    private int age;
    private String name;
    private String username;
    private String password;
}
