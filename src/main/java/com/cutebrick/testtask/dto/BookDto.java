package com.cutebrick.testtask.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class BookDto {
    private Integer id;
    private String bookName;
    private AuthorDto author;
    private String genre;
    private Integer releaseYear;
}
