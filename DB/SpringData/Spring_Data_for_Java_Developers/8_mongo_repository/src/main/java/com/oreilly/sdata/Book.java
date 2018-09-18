package com.oreilly.sdata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "publishDate")
@Document(collection = "book") //mapping to mongo data store
public class Book {

    @Id
    private String bookId;

    @TextIndexed(weight = 5)
    @Field(value = "title")
    private String title;

    private Date publishDate;

    private int pageCount;

    private BigDecimal price;

    private Author author;

    private List<String> tags = new ArrayList<>();

    private Library location;

    @TextIndexed(weight = 1)
    private String description;

    @TextScore
    private Float score;

}
