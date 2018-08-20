package com.cutebrick.testtask.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String bookName;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
    private List<Author> authors;
    @Column
    private String genre;
    @Column
    private Integer releaseYear;
}
