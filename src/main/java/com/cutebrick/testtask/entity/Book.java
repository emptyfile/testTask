package com.cutebrick.testtask.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "books", cascade = CascadeType.PERSIST)
    private List<Author> authors;
    @Column
    private String genre;
    @Column
    private Integer releaseYear;

    @PreRemove
    public void removeAuthor() {
        List<Author> authors = this.getAuthors();
        authors.forEach(a->a.getBooks().remove(this));
        this.setAuthors(new ArrayList<>());
    }
}
