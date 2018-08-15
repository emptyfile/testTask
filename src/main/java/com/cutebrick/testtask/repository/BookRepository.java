package com.cutebrick.testtask.repository;

import com.cutebrick.testtask.entity.Author;
import com.cutebrick.testtask.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> getAllPozhalustaByAuthor(Author author);
    List<Book> getBooksByBookName(String name);
    void deleteBookById(Integer id);
}
