package com.oreilly.sdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oreilly.sdata.repository.BookRepository;
import com.oreilly.sdata.util.BookUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/application-context.xml")
@Slf4j
public class TestMongoXMLConfig {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testInsert(){
        final Book book = BookUtil.create();

        bookRepository.save(book);
        // Inserting DBObject containing fields: [_class, _id, title, publishDate, pageCount, price, tags] in collection: book
    }

    @Test
    public void testFindByTitle(){
        final Book book = bookRepository.findByTitle("To Kill a Mocking Bird");
        //findOne using query: { "title" : "To Kill a Mocking Bird"} in db.collection: sandbox.book

        log.info(book.toString());
        // Book(bookId=5ba144447c04cb2d09cf1105, title=To Kill a Mocking Bird, publishDate=Tue Dec 30 02:00:00 EET 2014, pageCount=300, price=14.5, author=Author(authorId=null, firstName=Harper, lastName=Lee, country=United States), tags=[Classic, Best Seller], location=Library(libraryId=null, name=Library of Congress, coords=Point [x=-77.004665, y=38.888951]))
    }

    @Test
    public void testFullSearch(){
        final TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching("Gatsby");
        final List<Book> books = bookRepository.findAllBy(textCriteria);
        //find using query: { "$text" : { "$search" : "Gatsby"}} fields: { "score" : { "$meta" : "textScore"}} for class: class com.oreilly.sdata.Book in collection: book

        books.forEach(b -> log.info(b.toString()));
        // Book(bookId=5ba144447c04cb2d09cf1109, title=The Great Gatsby, publishDate=Mon Dec 29 23:46:40 EET 2014, pageCount=220, price=7.4, author=Author(authorId=null, firstName=F.Scoot, lastName=Fitzgerald, country=United States), tags=[Best Seller], location=Library(libraryId=null, name=San Francisco Public Library, coords=Point [x=-122.413964, y=37.787228]), description=null, score=3.75)
        // Book(bookId=5ba146677c045727399d9969, title=The Great Gatsby, publishDate=Mon Dec 29 23:46:40 EET 2014, pageCount=220, price=7.4, author=Author(authorId=null, firstName=F.Scoot, lastName=Fitzgerald, country=United States), tags=[Best Seller], location=Library(libraryId=null, name=San Francisco Public Library, coords=Point [x=-122.413964, y=37.787228]), description=null, score=3.75)
        // Book(bookId=5ba146c27c045368b51c69d1, title=The Great Gatsby, publishDate=Mon Dec 29 23:46:40 EET 2014, pageCount=220, price=7.4, author=Author(authorId=null, firstName=F.Scoot, lastName=Fitzgerald, country=United States), tags=[Best Seller], location=Library(libraryId=null, name=San Francisco Public Library, coords=Point [x=-122.413964, y=37.787228]), description=null, score=3.75)
    }

    @Test
    public void testJSONSearch(){
        final List<Book> books = bookRepository.findLargeBook(140);
        //find using query: { "pageCount" : { "$gt" : 140}} fields: { "title" : 1 , "pageCount" : 1 , "score" : { "$meta" : "textScore"}} for class: class com.oreilly.sdata.Book in collection: book

        books.forEach(b -> log.info(b.toString()));
        // Book(bookId=5ba0c133f2044529df086730, title=Using multi, publishDate=Tue Sep 18 11:11:14 CEST 2018, pageCount=141, price=15.00, author=null, tags=[], location=null, description=null, score=0.0)
        // Book(bookId=5ba0d62c67a636b85b2320db, title=Using multi, publishDate=Tue Sep 18 12:40:42 CEST 2018, pageCount=147, price=15.00, author=null, tags=[], location=null, description=null, score=0.0)
        // Book(bookId=5ba0d63e67a61ad208482d90, title=Using multi, publishDate=Tue Sep 18 12:40:59 CEST 2018, pageCount=148, price=15.00, author=null, tags=[], location=null, description=null, score=0.0)
        // Book(bookId=5ba0f289d3165e9b805702f6, title=Using multi, publishDate=Tue Sep 18 14:41:45 CEST 2018, pageCount=149, price=15.00, author=null, tags=[], location=null, description=null, score=0.0)
        // Book(bookId=5ba0f2c5d31694857605c027, title=Using multi, publishDate=Tue Sep 18 14:42:45 CEST 2018, pageCount=143, price=15.00, author=null, tags=[], location=null, description=null, score=0.0)
    }

    @Test
    public void testFindUpdatedByConverter(){
        final Book book = bookRepository.findByTitle("Moby Dick");

        log.info(book.toString());
    }

}
