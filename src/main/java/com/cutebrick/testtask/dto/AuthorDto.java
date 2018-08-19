package com.cutebrick.testtask.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class AuthorDto extends BaseAuthorDto{
    private List<BaseBookDto> books;
}
