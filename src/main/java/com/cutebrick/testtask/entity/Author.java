package com.cutebrick.testtask.entity;

import com.cutebrick.testtask.dto.AuthorDto;
import com.cutebrick.testtask.dto.BaseAuthorDto;
import com.cutebrick.testtask.dto.BaseBookDto;
import com.cutebrick.testtask.dto.BookDto;
import com.cutebrick.testtask.repository.AuthorRepository;
import com.cutebrick.testtask.repository.BookRepository;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Authors_Books",
            joinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")}
    )
    private List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @PreRemove
    public void removeBook() {
        List<Book> books = this.getBooks();
        books.forEach(b -> b.getAuthors().remove(this));
        this.setBooks(new ArrayList<>());
    }

    public void removeOneBook(Book book) {
        this.getBooks().remove(book);
        if (book.getAuthors().contains(this)) {
            book.removeOneAuthor(this);
        }
    }

    public void addOneBook(Book book) {
        this.getBooks().add(book);
        book.getAuthors().add(this);
    }

    public Author updateFromDto(AuthorDto authorDto, BookRepository bookRepository) {
        this.setFirstName(authorDto.getFirstName());
        this.setLastName(authorDto.getFirstName());

        List<Integer> bookDtoIds = authorDto.getBooks()
                .stream()
                .map(BaseBookDto::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<Book> books = bookRepository.getAllByIdIn(bookDtoIds);
        this.books.stream()
                .filter(book -> !bookDtoIds.contains(book.getId()))
                .forEach(book -> book.removeOneAuthor(this));
        books.stream().filter(book -> !this.books.contains(book)).forEach(book -> book.addOneAuthor(this));
        return this;
    }
}
