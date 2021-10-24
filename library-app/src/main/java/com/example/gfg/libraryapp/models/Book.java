package com.example.gfg.libraryapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int cost;

    @Enumerated(value = EnumType.STRING)
    private BookCategory bookCategory;

    @ManyToOne
    @JoinColumn
    private Student student;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("books")
    private Author author;

    @CreationTimestamp
    private Date addedDate;

    @OneToMany(mappedBy = "book")           // mappedBy value is the one from which the relation is made. From Book to Transactions is One To Many
    private List<Transaction> transactions;

}
