package com.oreilly.sdata;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Author {

    @Id
    private Long authorId;
    private String firstName;
    private String lastName;
    private String country;

}
