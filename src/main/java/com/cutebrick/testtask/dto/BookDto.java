package com.cutebrick.testtask.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class BookDto extends BaseBookDto{
    private List<BaseAuthorDto> authors;
    private String genre;
    private Integer releaseYear;
}
