package com.cutebrick.testtask.service;

import com.cutebrick.testtask.entity.Author;
import com.cutebrick.testtask.entity.Book;
import com.cutebrick.testtask.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.getAllPozhalustaByAuthor(author);
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
}
