package com.cutebrick.testtask.service;

import com.cutebrick.testtask.dto.BaseAuthorDto;
import com.cutebrick.testtask.dto.BookDto;
import com.cutebrick.testtask.entity.Author;
import com.cutebrick.testtask.entity.Book;
import com.cutebrick.testtask.repository.AuthorRepository;
import com.cutebrick.testtask.repository.BookRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    ModelMapper mm = new ModelMapper();

    Converter<List<Author>, List<BaseAuthorDto>> bookStringConverter = (ctx) -> ctx.getSource()
            .stream()
            .map(a -> new BaseAuthorDto(a.getId(), a.getFirstName(), a.getLastName()))
            .collect(Collectors.toList());

    @PostConstruct
    public void init() {
        mm.typeMap(Book.class, BookDto.class)
                .addMappings(mapper -> mapper.using(bookStringConverter)
                        .map(Book::getAuthors, BookDto::setAuthors));
    }

    public List<BookDto> getAllBooks() {
        List<Book> all = bookRepository.findAll();
        return all.stream().map(a -> mm.map(a, BookDto.class)).collect(Collectors.toList());

    }

    public List<Book> getBooksByAuthor(Author author) {
        //return bookRepository.getAllPozhalustaByAuthor(author);
        return null;
    }

    public List<Book> getBooksByName(String name) {
        return bookRepository.getBooksByBookName(name);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(String id) {
        return bookRepository.getOne(Integer.parseInt(id));
    }

    public void deleteBook(String id) {
        bookRepository.deleteBookById(Integer.parseInt(id));
    }

    public Book updateBook(String id, Book book) {
        int intId = Integer.parseInt(id);
        if (intId == book.getId()) {
            return bookRepository.save(book);
        } else {
            throw new IllegalArgumentException("Id`s are different.");
        }
    }

    public List<BookDto> getAllBooksByAuthorId(Integer authorId) {
        List<Book> books = authorRepository.getOne(authorId).getBooks();
        return books.stream().map(b->mm.map(b, BookDto.class)).collect(Collectors.toList());
    }
}
