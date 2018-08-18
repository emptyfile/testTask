package com.cutebrick.testtask.controller;

import com.cutebrick.testtask.dto.AuthorDto;
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
    public List<AuthorDto> getAllAuthors() {
        System.out.println(authorService.getAllAuthors());
        return authorService.getAllAuthors();

    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable("id") String id) {
        return authorService.getAuthorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") String id) {
        authorService.deleteAuthor(id);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable("id") String id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }
}
