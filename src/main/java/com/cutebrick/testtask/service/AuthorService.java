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

}
