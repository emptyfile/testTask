package com.cutebrick.testtask.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * This class provides Data Transfer Object for Books.
 * It needs to separate information about books.
 *
 * @author Peter Ursatii
 */
@ToString
@Data
public class BookDto extends BaseBookDto{
    private List<BaseAuthorDto> authors;
    private String genre;
    private Integer releaseYear;
}
