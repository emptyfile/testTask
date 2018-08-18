package com.cutebrick.testtask.service;

import com.cutebrick.testtask.dto.AuthorDto;
import com.cutebrick.testtask.entity.Author;
import com.cutebrick.testtask.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    ModelMapper mm = new ModelMapper();


    public List<AuthorDto> getAllAuthors() {
        List<Author> all = authorRepository.findAll();
        List<AuthorDto> authorDtos = new ArrayList<AuthorDto>();
        mm.map(all, authorDtos);
        return all.stream().map(a->mm.map(a, AuthorDto.class)).collect(Collectors.toList());
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
