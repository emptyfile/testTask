package com.cutebrick.testtask.repository;

import com.cutebrick.testtask.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> getAuthorByFirstNameAndLastName(String firstName, String lastName);
}
