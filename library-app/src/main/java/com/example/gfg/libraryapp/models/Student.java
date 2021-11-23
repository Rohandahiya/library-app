package com.example.gfg.libraryapp.models;

import com.example.gfg.libraryapp.security.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;
    private int age;

    private String name;

    @OneToMany(mappedBy = "student")
    private List<Book> books;

    @OneToMany(mappedBy = "student")
    private List<Transaction> transactions;

    @CreationTimestamp
    private Date createdDate;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties("student")
    private User user;
}
