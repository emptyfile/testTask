package com.cutebrick.testtask.service;

import com.cutebrick.testtask.dto.BaseAuthorDto;
import com.cutebrick.testtask.dto.BookDto;
import com.cutebrick.testtask.entity.Author;
import com.cutebrick.testtask.entity.Book;
import com.cutebrick.testtask.repository.AuthorRepository;
import com.cutebrick.testtask.repository.BookRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for Books.
 * Service is providing most of the functionality.
 * It receives calls from controllers.
 *
 * @author Peter Ursatii
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    ModelMapper mm = new ModelMapper();

    Converter<List<Author>, List<BaseAuthorDto>> bookStringConverter = (ctx) -> ctx.getSource()
            .stream()
            .map(a -> new BaseAuthorDto(a.getId(), a.getFirstName(), a.getLastName()))
            .collect(Collectors.toList());

    Converter<List<BaseAuthorDto>, List<Author>> baseDtoToAuthor = (ctx) -> ctx.getSource()
            .stream()
            .map(a-> authorRepository.getOne(a.getId()))
            .collect(Collectors.toList());

    /**
     * This method adds a new mappings.
     */
    @PostConstruct
    public void init() {
        mm.typeMap(Book.class, BookDto.class)
                .addMappings(mapper -> mapper.using(bookStringConverter)
                        .map(Book::getAuthors, BookDto::setAuthors));

        mm.typeMap(BookDto.class, Book.class)
                .addMappings(mapper -> mapper.using(baseDtoToAuthor)
                    .map(BookDto::getAuthors, Book::setAuthors));
    }

    /**
     * This method gets all of the books and maps them to BookDto.
     *
     * @return List of BookDto`s.
     */
    public List<BookDto> getAllBooks() {
        List<Book> all = bookRepository.findAll();
        return all.stream().map(a -> mm.map(a, BookDto.class)).collect(Collectors.toList());

    }

    /**
     * This method creates a new book and sets this book
     * to all of the authors that wrote it.
     *
     * @param bookDto
     */
    public void createBook(BookDto bookDto) {
        Book book = mm.map(bookDto, Book.class);
        book.getAuthors().forEach(author -> author.getBooks().add(book));
        bookRepository.save(book);
    }

    /**
     * This method gets book by books id and maps it to BookDto.
     *
     * @param id
     * @return BookDto
     */
    public BookDto getBookById(String id) {
        return mm.map(bookRepository.getOne(Integer.parseInt(id)), BookDto.class);
    }

    /**
     * This method deletes book by books id.
     *
     * @param id
     */
    @Transactional
    public void deleteBook(String id) {
        bookRepository.deleteBookById(Integer.parseInt(id));
    }

    /**
     * This method creates a new book by calling a updateFromDto() method.
     * And saves it if ids are equal.
     *
     * @param id
     * @param bookDto
     */
    public void updateBook(String id, BookDto bookDto) {
        int intId = Integer.parseInt(id);
        Book book = bookRepository.getOne(bookDto.getId()).updateFromDto(bookDto, authorRepository);
        if (intId == book.getId()) {
             bookRepository.save(book);
        } else {
            throw new IllegalArgumentException("Id`s are different.");
        }
    }

    /**
     * This method gets all books by authors id and maps them to BookDto.
     *
     * @param authorId
     * @return List of BookDto`s.
     */
    public List<BookDto> getAllBooksByAuthorId(Integer authorId) {
        List<Book> books = authorRepository.getOne(authorId).getBooks();
        return books.stream().map(b->mm.map(b, BookDto.class)).collect(Collectors.toList());
    }
}
