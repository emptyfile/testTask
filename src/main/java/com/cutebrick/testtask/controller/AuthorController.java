package com.cutebrick.testtask.controller;

import com.cutebrick.testtask.dto.AuthorDto;
import com.cutebrick.testtask.dto.BaseAuthorDto;
import com.cutebrick.testtask.entity.Author;
import com.cutebrick.testtask.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Authors.
 * Controller is used to receive information from web
 * and pass it to Services and vise versa.
 *
 * @author Peter Ursatii
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    /**
     * This method gets list of authors depending on parameter
     * that is passed to the method.
     * If bookId is null method will return a list of all authors.
     * If bookId isn`t null method will return all Author that wrote this book.
     *
     * @param bookId
     * @return List of Authors
     */
    @GetMapping
    public List<AuthorDto> getAllAuthors(@RequestParam(name = "book_id",required = false) Integer bookId) {
        if (bookId == null) {
            return authorService.getAllAuthors();
        } else {
            return authorService.getAllAuthorsByBookId(bookId);
        }
    }

    /**
     * This method returns BaseAuthorDto`s for all of the authors.
     * @return List of BaseAuthorDto`s
     */
    @GetMapping("/base")
    public List<BaseAuthorDto> getAllBaseAuthorDtos() {
        return authorService.getAllBaseAuthorDtos();
    }

    /**
     * This method is used to get BaseAuthorDto by authors id.
     * @param id
     * @return BaseAuthorDto
     */
    @GetMapping("/base/{id}")
    public BaseAuthorDto getBaseAuthor(@PathVariable("id") String id) {
        return authorService.getBaseAuthorById(id);
    }

    /**
     * This method creates a new author.
     * @param author
     */
    @PostMapping(consumes = "application/json")
    public void createAuthor(@RequestBody Author author) {
        authorService.createAuthor(author);
    }

    /**
     * This method gets one author by authors id.
     * @param id
     * @return Author
     */
    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable("id") String id) {
        return authorService.getAuthorById(id);
    }

    /**
     * This method is deleting one author by authors id.
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") String id) {
        authorService.deleteAuthor(id);
    }

    /**
     * This method updates existing author.
     * @param id
     * @param authorDto
     */
    @PutMapping("/{id}")
    public void updateAuthor(@PathVariable("id") String id, @RequestBody AuthorDto authorDto) {
        authorService.updateAuthor(id, authorDto);
    }


}
