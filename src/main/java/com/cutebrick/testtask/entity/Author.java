package com.cutebrick.testtask.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
    private List<Book> books;
}
