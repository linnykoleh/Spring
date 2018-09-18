package com.oreilly.sdata;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.oreilly.sdata.config.MongoDBConfig;
import com.oreilly.sdata.data.entities.Book;
import com.oreilly.sdata.util.BookUtil;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoDBConfig.class})
@Slf4j
public class TestMongoJavaConfig {

    @Autowired
    private MongoTemplate template;

    @Autowired
    private MongoDbFactory mongoDbFactory;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    public void testInsertViaMongoDbFactory(){
        final DB db = mongoDbFactory.getDb();
        final DBCollection collection = db.getCollection("book");
        collection.insert(new BasicDBObject().append("title", "Harry Potter"));
    }

    @Test
    public void testInsert() {
        final Book book = BookUtil.create();
        template.insert(book);
        // Inserting DBObject containing fields: [_class, _id, title, publishDate, pageCount, price] in collection: book
    }

    @Test
    public void testInsert2() {
        final Book book = BookUtil.create();
        template.insert(book, "library");
        // Inserting DBObject containing fields: [_class, _id, title, publishDate, pageCount, price, tags] in collection: library
    }

    @Test
    public void testInsert3() {
        final List<Book> books = BookUtil.create(4);
        mongoOperations.insert(books, Book.class);
        // Inserting list of DBObjects containing 4 items
    }

    @Test
    public void testInsert4() {
        final Book book = BookUtil.create();
        mongoOperations.save(book, "id1");
        // save new or update old by id
        // Saving DBObject containing fields: [_class, _id, title, publishDate, pageCount, price, tags]
    }

    @Test
    public void testUpdateFirst() {
        final Query query = Query.query(Criteria.where("title").is("Harry Potter"));
        final Update update = Update.update("title", "Something else");
        mongoOperations.updateFirst(query, update, Book.class);
        // Calling update using query: { "title" : "Harry Potter"} and update: { "$set" : { "title" : "Something else"}} in collection: book
    }

    @Test
    public void testUpdateMulti() {
        final Query query = new Query();
        final Update update = Update.update("title", "Using multi");
        mongoOperations.updateMulti(query, update, Book.class);
        // Calling update using query: { } and update: { "$set" : { "title" : "Using multi"}} in collection: book
    }

    @Test
    public void testUpsert() {
        final Query query = new Query(
                Criteria.where("title").is("To Ill a Mocking Bird")
                        .and("author.firstName").is("Bob")
                        .and("author.lastName").is("Smith"));

        final Update update = Update.update("pageCount", 1);
        mongoOperations.upsert(query, update, Book.class);
        // Calling update using query: { "title" : "To Ill a Mocking Bird" , "author.firstName" : "Bob" , "author.lastName" : "Smith"} and update: { "$set" : { "pageCount" : 1}} in collection: book
    }

    @Test
    public void testRemove() {
        final Query query = new Query(
                Criteria.where("title").is("To Ill a Mocking Bird")
                        .and("author.firstName").is("Bob")
                        .and("author.lastName").is("Smith"));

        mongoOperations.remove(query, Book.class);
        // Remove using query: { "title" : "To Ill a Mocking Bird" , "author.firstName" : "Bob" , "author.lastName" : "Smith"} in collection: book.
    }

    @Test
    public void testFindById() {
        final Book book = BookUtil.create();
        mongoOperations.save(book);
        // Saving DBObject containing fields: [_class, _id, title, publishDate, pageCount, price, tags]

        final Book bookDB = mongoOperations.findById(book.getBookId(), Book.class);
        // findOne using query: { "bookId" : "5ba0fc49852e5ab875dcc515"} fields: null for class: class com.oreilly.sdata.data.entities.Book in collection: book

        log.info(bookDB.toString());
        // Book(bookId=5ba0fc49852e5ab875dcc515, title=To Kill A Mockingbird, publishDate=Tue Sep 18 15:23:18 CEST 2018, pageCount=108, price=15.00, author=null, tags=[])
    }

    @Test
    public void testFindOne() {
        final Book book = BookUtil.create();
        mongoOperations.save(book);
        // Saving DBObject containing fields: [_class, _id, title, publishDate, pageCount, price, tags]

        final Criteria criteria = Criteria.where("bookId").is(book.getBookId());
        final Query query = new Query(criteria);
        final Book bookDB = mongoOperations.findOne(query, Book.class);
        // findOne using query: { "bookId" : "5ba0fcdb852eff789eb07a7e"} fields: null for class: class com.oreilly.sdata.data.entities.Book in collection: book

        log.info(bookDB.toString());
        // Book(bookId=5ba0fcdb852eff789eb07a7e, title=1984, publishDate=Tue Sep 18 15:25:45 CEST 2018, pageCount=145, price=15.00, author=null, tags=[])
    }

    @Test
    public void testCriteriaAndQuery() {
        final Criteria criteria = Criteria.where("title").regex(Pattern.compile(".*of."));
        final Query query = new Query(criteria).addCriteria(Criteria.where("pageCount").gt(300));

        final List<Book> books = mongoOperations.find(query, Book.class);
        // find using query: { "title" : { "$regex" : ".*of."} , "pageCount" : { "$gt" : 300}} fields: null for class: class com.oreilly.sdata.data.entities.Book in collection: book

        books.forEach(b -> log.info(b.toString()));
    }
}
