package com.oreilly.sdata;

import com.oreilly.sdata.data.entities.Book;
import com.oreilly.sdata.repositories.BookRepository;
import com.oreilly.sdata.util.BookUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/application-context.xml")
@Slf4j
public class TestBookRepository {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testGlobalCustomization() {
        final List<Book> books = bookRepository.findByIds(2L, 7L);
//         select * from BOOK b where b.BOOK_ID in (? , ?)

        books.forEach(b -> log.info(b.toString()));
//         Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=301, price=15.00, author=Author(authorId=11, firstName=Ken, lastName=Kesey, country=Russia))
//         Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-19 00:00:00.0, pageCount=270, price=15.00, author=Author(authorId=6, firstName=Charles, lastName=Dickens, country=England))
    }

    @Test
    public void testAuditingSave() {
        final Book book = BookUtil.create();
        bookRepository.save(book);
        // insert into BOOK (BOOK_ID, AUTHOR_ID, createdBy, createdDate, lastModifiedBy, lastModifiedDate, PAGE_COUNT, PRICE, PUBLISH_DATE, TITLE) values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)

        final Book bookDb = bookRepository.findOne(book.getBookId());
        // select * from BOOK b left outer join AUTHOR a on b.AUTHOR_ID=a.AUTHOR_ID where b.BOOK_ID=?
        //
        System.out.println(bookDb);
        // Book(bookId=15,
        //      title=The Scarlet Letter,
        //      publishDate=2018-09-14 20:51:08.274,
        //      pageCount=107,
        //      price=15.00,
        //      author=null,
        //      createdBy=Linnyk Oleh,
        //      lastModifiedBy=Linnyk Oleh,
        //      createdDate=Fri Sep 14 20:51:08 EEST 2018,
        //      lastModifiedDate=Fri Sep 14 20:51:08 EEST 2018
        // )
    }

    @Test
    public void testModifyQuery() {
        final int entityUpdatedNumber = bookRepository.setPageCount(1000, "Animal Farm");
        System.out.println("Updated " + entityUpdatedNumber + " entities");
        // Updated 1 entities

        final List<Book> all = bookRepository.findAll();
        // select * from BOOK b

        all.forEach(b -> log.info(b.toString()));
        // Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=230, price=11.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States), createdBy=null, lastModifiedBy=null, createdDate=null, lastModifiedDate=null)
        // Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=301, price=15.00, author=Author(authorId=11, firstName=Ken, lastName=Kesey, country=Russia), createdBy=null, lastModifiedBy=null, createdDate=null, lastModifiedDate=null)
        // Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=210, price=13.00, author=Author(authorId=5, firstName=Ernest, lastName=Hemmingway, country=United States), createdBy=null, lastModifiedBy=null, createdDate=null, lastModifiedDate=null)
        // Book(bookId=4, title=War and Peace, publishDate=1955-11-21 00:00:00.0, pageCount=400, price=15.00, author=Author(authorId=10, firstName=Leo, lastName=Tolstoy, country=Russia), createdBy=null, lastModifiedBy=null, createdDate=null, lastModifiedDate=null)
        // Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-14 00:00:00.0, pageCount=350, price=16.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States), createdBy=null, lastModifiedBy=null, createdDate=null, lastModifiedDate=null)
        // Book(bookId=6, title=Design Patterns, publishDate=1996-11-25 00:00:00.0, pageCount=120, price=14.00, author=Author(authorId=9, firstName=Erich, lastName=Gamma, country=United States), createdBy=null, lastModifiedBy=null, createdDate=null, lastModifiedDate=null)
        // Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-19 00:00:00.0, pageCount=270, price=15.00, author=Author(authorId=6, firstName=Charles, lastName=Dickens, country=England), createdBy=null, lastModifiedBy=null, createdDate=null, lastModifiedDate=null)
        // Book(bookId=8, title=Animal Farm, publishDate=1965-06-08 00:00:00.0, pageCount=1000, price=22.00, author=Author(authorId=2, firstName=George, lastName=Orwell, country=United States), createdBy=null, lastModifiedBy=null, createdDate=null, lastModifiedDate=null)
        // Book(bookId=9, title=Lord of the Flies, publishDate=1973-03-08 00:00:00.0, pageCount=300, price=119.00, author=Author(authorId=4, firstName=William, lastName=Golding, country=England), createdBy=null, lastModifiedBy=null, createdDate=null, lastModifiedDate=null)
        // Book(bookId=10, title=Great Expectations, publishDate=1878-10-08 00:00:00.0, pageCount=140, price=13.66, author=Author(authorId=6, firstName=Charles, lastName=Dickens, country=England), createdBy=null, lastModifiedBy=null, createdDate=null, lastModifiedDate=null)
        // Book(bookId=11, title=The Count of Monte Cristo, publishDate=1903-12-08 00:00:00.0, pageCount=160, price=22.25, author=Author(authorId=7, firstName=Alexandre, lastName=Dumas, country=France), createdBy=null, lastModifiedBy=null, createdDate=null, lastModifiedDate=null)
        // Book(bookId=12, title=The Scarlet Letter, publishDate=1957-10-08 00:00:00.0, pageCount=170, price=16.00, author=Author(authorId=8, firstName=Nathaniel, lastName=Hawthorne, country=United States), createdBy=null, lastModifiedBy=null, createdDate=null, lastModifiedDate=null)
        // Book(bookId=13, title=Hamlet, publishDate=1655-11-08 00:00:00.0, pageCount=180, price=19.56, author=Author(authorId=3, firstName=William, lastName=Shakespeare, country=England), createdBy=null, lastModifiedBy=null, createdDate=null, lastModifiedDate=null)
        // Book(bookId=14, title=The Old Man and the Sea, publishDate=1952-09-08 00:00:00.0, pageCount=190, price=23.00, author=Author(authorId=5, firstName=Ernest, lastName=Hemmingway, country=United States), createdBy=null, lastModifiedBy=null, createdDate=null, lastModifiedDate=null)
    }
}
