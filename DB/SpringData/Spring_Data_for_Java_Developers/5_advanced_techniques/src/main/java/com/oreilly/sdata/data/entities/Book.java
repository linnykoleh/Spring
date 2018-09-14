package com.oreilly.sdata.data.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "publishDate")
@Entity
@Table(name = "BOOK")
@NamedQueries({
        @NamedQuery(name="Book.queryOne", query="select b from Book b"),
        @NamedQuery(name="Book.queryTwo", query="select b from Book b where b.pageCount > ?1"),
        @NamedQuery(name="Book.queryThree", query="select b from Book b where b.title = :title")
})
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PUBLISH_DATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date publishDate;

    @Column(name = "PAGE_COUNT")
    private int pageCount;

    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="AUTHOR_ID")
    private Author author;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    private String createdDate;

    @LastModifiedDate
    private String lastModifiedDate;

}
