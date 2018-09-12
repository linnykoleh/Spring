package com.oreilly.sdata.data.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long bookId;
    private String title;
    private Date publishDate;
    private int pageCount;
    private BigDecimal price;

}
