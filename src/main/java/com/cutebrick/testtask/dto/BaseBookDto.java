package com.cutebrick.testtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class provides Data Transfer Object for Books.
 * It needs to avoid stackOverflow.
 *
 * @author Peter Ursatii
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseBookDto {
    private Integer id;
    private String bookName;
}
