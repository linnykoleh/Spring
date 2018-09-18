package com.oreilly.sdata.data.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "publishDate")
@Document(collection = "book") //mapping to mongo data store
public class Book {

    @Id //Id for mongo db
    private String bookId;

    @Field(value = "title") //field for mongo db
    private String title;

    private Date publishDate;

    private int pageCount;

    private BigDecimal price;

    private Author author;

    private List<String> tags = new ArrayList<>();

}
