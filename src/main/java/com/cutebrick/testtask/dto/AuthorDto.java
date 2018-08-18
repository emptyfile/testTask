package com.cutebrick.testtask.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class AuthorDto {
    private Integer id;
    private String firstName;
    private String lastName;
}
