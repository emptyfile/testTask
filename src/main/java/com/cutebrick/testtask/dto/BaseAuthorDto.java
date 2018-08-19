package com.cutebrick.testtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseAuthorDto {
    private Integer id;
    private String firstName;
    private String lastName;
}
