package com.cutebrick.testtask.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class BookDto extends BaseBookDto{
    private BaseAuthorDto author;
    private String genre;
    private Integer releaseYear;
}
