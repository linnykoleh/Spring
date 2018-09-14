package com.oreilly.sdata.data.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "BOOK")
@NamedQueries({
        @NamedQuery(name="Book.queryOne", query="select b from Book b"),
        @NamedQuery(name="Book.queryTwo", query="select b from Book b where b.pageCount > ?1"),
        @NamedQuery(name="Book.queryThree", query="select b from Book b where b.title = :title")
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PUBLISH_DATE")
    private Date publishDate;

    @Column(name = "PAGE_COUNT")
    private int pageCount;

    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="AUTHOR_ID")
    private Author author;

}
