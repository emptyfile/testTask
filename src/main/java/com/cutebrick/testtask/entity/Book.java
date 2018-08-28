package com.cutebrick.testtask.entity;

import com.cutebrick.testtask.dto.AuthorDto;
import com.cutebrick.testtask.dto.BaseAuthorDto;
import com.cutebrick.testtask.dto.BookDto;
import com.cutebrick.testtask.repository.AuthorRepository;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id);
    }

    @PreRemove
    public void removeAuthor() {
        List<Author> authors = this.getAuthors();
        authors.forEach(a -> a.getBooks().remove(this));
        this.setAuthors(new ArrayList<>());
    }

    public Book updateFromDto(BookDto bookDto, AuthorRepository authorRepository) {
        this.setBookName(bookDto.getBookName());
        this.setGenre(bookDto.getGenre());
        this.setReleaseYear(bookDto.getReleaseYear());

        List<Integer> authorDtoIds = bookDto.getAuthors()
                .stream()
                .map(BaseAuthorDto::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<Author> authors = authorRepository.getAllByIdIn(authorDtoIds);
        this.authors.stream()
                .filter(author -> !authorDtoIds.contains(author.getId()))
                .forEach(author -> author.removeOneBook(this));
        authors.stream().filter(author -> !this.authors.contains(author)).forEach(author -> author.addOneBook(this));
        return this;
    }

    public void removeOneAuthor(Author author) {
        this.getAuthors().remove(author);
        if (author.getBooks().contains(this)) {
            author.removeOneBook(this);
        }
    }

    public void addOneAuthor(Author author) {
        this.getAuthors().add(author);
        author.getBooks().add(this);
    }
}
