package com.cutebrick.testtask;

import com.cutebrick.testtask.entity.Author;
import com.cutebrick.testtask.entity.Book;
import com.cutebrick.testtask.repository.AuthorRepository;
import com.cutebrick.testtask.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class TesttaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesttaskApplication.class, args);
	}


}
