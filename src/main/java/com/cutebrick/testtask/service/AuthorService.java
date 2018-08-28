package com.cutebrick.testtask.service;

import com.cutebrick.testtask.dto.AuthorDto;
import com.cutebrick.testtask.dto.BaseAuthorDto;
import com.cutebrick.testtask.dto.BaseBookDto;
import com.cutebrick.testtask.entity.Author;
import com.cutebrick.testtask.entity.Book;
import com.cutebrick.testtask.repository.AuthorRepository;
import com.cutebrick.testtask.repository.BookRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    ModelMapper mm = new ModelMapper();

    Converter<List<Book>, List<BaseBookDto>> bookStringConverter = (ctx) -> ctx.getSource()
            .stream()
            .map(a->new BaseBookDto(a.getId(),a.getBookName()))
            .collect(Collectors.toList());
    private List<BaseAuthorDto> allBaseAuthorDtos;

    @PostConstruct
    public void init() {
        mm.typeMap(Author.class, AuthorDto.class)
                .addMappings(mapper -> mapper.using(bookStringConverter)
                        .map(Author::getBooks, AuthorDto::setBooks));
    }

    public List<AuthorDto> getAllAuthors() {
        List<Author> all = authorRepository.findAll();
        return all.stream().map(a -> mm.map(a, AuthorDto.class)).collect(Collectors.toList());
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

    public BaseAuthorDto getBaseAuthorById(String id) {
        Author author = authorRepository.getOne(Integer.parseInt(id));
        return mm.map(author, BaseAuthorDto.class);
    }

    @Transactional
    public void deleteAuthor(String id) {
        authorRepository.deleteAuthorById(Integer.parseInt(id));
    }

    public void updateAuthor(String id, AuthorDto authorDto) {
        int intId = Integer.parseInt(id);
        Author author = mm.map(authorDto, Author.class);
        author.setBooks(authorRepository.getOne(authorDto.getId()).getBooks());
        if (intId == author.getId()) {
            authorRepository.save(author);
        } else {
            throw new IllegalArgumentException("Id`s are different.");
        }
    }

    public List<AuthorDto> getAllAuthorsByBookId(Integer bookId) {
        List<Author> all = bookRepository.getOne(bookId).getAuthors();
        return all.stream().map(a -> mm.map(a, AuthorDto.class)).collect(Collectors.toList());
    }

    public List<BaseAuthorDto> getAllBaseAuthorDtos() {
        List<Author> all = authorRepository.findAll();
        return all.stream().map(a->mm.map(a, BaseAuthorDto.class)).collect(Collectors.toList());

    }
}
