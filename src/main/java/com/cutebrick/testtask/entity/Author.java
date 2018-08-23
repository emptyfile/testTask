package com.cutebrick.testtask.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Authors_Books",
            joinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")}
    )
    private List<Book> books;

    @PreRemove
    public void removeBook() {
        List<Book> books = this.getBooks();
        books.forEach(b -> b.getAuthors().remove(this));
        this.setBooks(new ArrayList<>());
    }
}
