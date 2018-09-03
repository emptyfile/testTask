package com.cutebrick.testtask.controller;

import com.cutebrick.testtask.dto.BookDto;
import com.cutebrick.testtask.entity.Book;
import com.cutebrick.testtask.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Books.
 * Controller is used to receive information from web
 * and pass it to Services and vise versa.
 *
 * @author Peter Ursatii
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * This method gets list of books depending on parameter
     * that is passed to the method.
     * If authorId is null method will return a list of all authors.
     * If authorId isn`t null method will return all Author that wrote this book.
     *
     * @param authorId
     * @return List of Books
     */
    @GetMapping
    public List<BookDto> getAllBooks(@RequestParam(name = "author_id",required = false) Integer authorId) {
        System.out.println(authorId);
        if (authorId==null) {
            return bookService.getAllBooks();
        }else {
            return bookService.getAllBooksByAuthorId(authorId);
        }
    }

    /**
     * This method creates a new Book.
     * @param book
     */
    @PostMapping(consumes = "application/json")
    public void createBook(@RequestBody BookDto book) {
        bookService.createBook(book);
    }

    /**
     * This method gets one books by books id.
     * @param id
     * @return Book
     */
    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") String id) {
        return bookService.getBookById(id);
    }

    /**
     * This method deletes one book by books id.
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") String id) {
        bookService.deleteBook(id);
    }

    /**
     * This method updates existing book.
     * @param id
     * @param book
     */
    @PutMapping("/{id}")
    public void updateBook(@PathVariable("id") String id, @RequestBody BookDto book) {
        bookService.updateBook(id, book);
    }
}
