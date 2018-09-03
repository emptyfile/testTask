package com.cutebrick.testtask.controller;

import com.cutebrick.testtask.dto.BookDto;
import com.cutebrick.testtask.entity.Book;
import com.cutebrick.testtask.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDto> getAllBooks(@RequestParam(name = "author_id",required = false) Integer authorId) {
        System.out.println(authorId);
        if (authorId==null) {
            return bookService.getAllBooks();
        }else {
            return bookService.getAllBooksByAuthorId(authorId);
        }
    }

    @PostMapping(consumes = "application/json")
    public void createBook(@RequestBody BookDto book) {
        bookService.createBook(book);
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") String id) {
        return bookService.getBookById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") String id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable("id") String id, @RequestBody BookDto book) {
        bookService.updateBook(id, book);
    }
}
