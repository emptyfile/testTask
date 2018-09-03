package com.cutebrick.testtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class provides Data Transfer Object for Authors.
 * It needs to separate List of BaseBookDto`s from other authors fields.
 *
 * @author Peter Ursatii
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseAuthorDto {
    private Integer id;
    private String firstName;
    private String lastName;
}
