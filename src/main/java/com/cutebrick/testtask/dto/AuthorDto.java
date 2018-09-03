package com.cutebrick.testtask.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * This class provides Data Transfer Object for Authors.
 * It needs to separate List of BaseBookDto`s from author.
 *
 * @author Peter Ursatii
 */
@ToString
@Data
public class AuthorDto extends BaseAuthorDto{
    private List<BaseBookDto> books;
}
