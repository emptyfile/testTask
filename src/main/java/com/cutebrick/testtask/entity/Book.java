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

/**
 * Entity for Books.
 * Entity represents a Author Table in database.
 *
 * @author Peter Ursatii
 */
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

    /**
     * This method is called before deleting a book.
     * It deletes this book from all of the authors that wrote it.
     */
    @PreRemove
    public void removeAuthor() {
        List<Author> authors = this.getAuthors();
        authors.forEach(a -> a.getBooks().remove(this));
        this.setAuthors(new ArrayList<>());
    }

    /**
     * This method is called before updating an existing book.
     * At first it sets book name, genre and release year to the book.
     * Then it gets ids of all authors in bookDto and gets authors by this ids.
     * After that it deletes this book from all author that was in book but
     * is not in bookDto.
     * And then it adds this book to new authors that in bookDto but wasn`t in book previously.
     *
     * @param bookDto
     * @param authorRepository
     * @return
     */
    public Book updateFromDto(BookDto bookDto, AuthorRepository authorRepository) {
        this.setBookName(bookDto.getBookName());
        this.setGenre(bookDto.getGenre());
        this.setReleaseYear(bookDto.getReleaseYear());

        List<Integer> authorDtoIds = bookDto.getAuthors()
                .stream()
                .map(baseAuthorDto -> baseAuthorDto.getId())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<Author> authors = authorRepository.getAllByIdIn(authorDtoIds);
        this.authors.stream()
                .filter(author -> !authorDtoIds.contains(author.getId()))
                .forEach(author -> author.removeOneBook(this));
        authors.stream().filter(author -> !this.authors.contains(author)).forEach(author -> author.addOneBook(this));
        return this;
    }

    /**
     * This method removes author from this book.
     * And removes this book from that author.
     *
     * @param author
     */
    public void removeOneAuthor(Author author) {
        this.getAuthors().remove(author);
        if (author.getBooks().contains(this)) {
            author.removeOneBook(this);
        }
    }

    /**
     * This method adds author to this book.
     * And adds this book to author.
     *
     * @param author
     */
    public void addOneAuthor(Author author) {
        this.getAuthors().add(author);
        author.getBooks().add(this);
    }
}
