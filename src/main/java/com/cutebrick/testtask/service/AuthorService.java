package com.cutebrick.testtask.service;

import com.cutebrick.testtask.entity.Author;
import com.cutebrick.testtask.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> getAuthorByName(String firstName, String lastName) {
        return authorRepository.getAuthorByFirstNameAndLastName(firstName, lastName);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author getAuthorById(String id) {
        return authorRepository.getOne(Integer.parseInt(id));
    }

    public void deleteAuthor(String id){
        authorRepository.deleteAuthorById(Integer.parseInt(id));
    }

    public Author updateAuthor(String id, Author author) {
        int intId = Integer.parseInt(id);
        if (intId==author.getId()) {
            return authorRepository.save(author);
        } else {
            throw new IllegalArgumentException("Id`s are different.");
        }
    }
}
