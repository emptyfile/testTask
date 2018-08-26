package com.cutebrick.testtask.controller;

import com.cutebrick.testtask.dto.AuthorDto;
import com.cutebrick.testtask.dto.BaseAuthorDto;
import com.cutebrick.testtask.entity.Author;
import com.cutebrick.testtask.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<AuthorDto> getAllAuthors(@RequestParam(name = "book_id",required = false) Integer bookId) {
        System.out.println(bookId);
        if (bookId == null) {
            System.out.println(authorService.getAllAuthors());
            return authorService.getAllAuthors();
        } else {
            System.out.println(authorService.getAllAuthorsByBookId(bookId));
            return authorService.getAllAuthorsByBookId(bookId);
        }
    }

    @GetMapping("/base")
    public List<BaseAuthorDto> getAllBaseAuthorDtos() {
        return authorService.getAllBaseAuthorDtos();
    }

    @PostMapping(consumes = "application/json")
    public void createAuthor(@RequestBody Author author) {
        authorService.createAuthor(author);
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable("id") String id) {
        return authorService.getAuthorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") String id) {
        System.out.println("1 "+id);
        authorService.deleteAuthor(id);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable("id") String id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }


}
