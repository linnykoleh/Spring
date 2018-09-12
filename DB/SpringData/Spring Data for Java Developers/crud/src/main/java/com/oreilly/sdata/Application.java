package com.oreilly.sdata;

import com.oreilly.sdata.data.entities.Book;
import com.oreilly.sdata.repositories.BookRepository;
import com.oreilly.sdata.util.BookUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private static BookRepository bookRepository;

    public static void main(String[] args) {
        final ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/application-context.xml");
        bookRepository = context.getBean(BookRepository.class);

        /*CRUD*/
        create();
        read();
        update();
        delete();
    }

    private static void create(){
        System.out.println("=========================");
        System.out.println("CREATE....");
        System.out.println("=========================");

        System.out.println("save(entity)....");
        final Book book = BookUtil.create();
        bookRepository.save(book);
        // insert into BOOK (BOOK_ID, PAGE_COUNT, PRICE, PUBLISH_DATE, TITLE) values (null, ?, ?, ?, ?)

        System.out.println("save(entities)....");
        final List<Book> books = BookUtil.create(3);
        bookRepository.save(books);
        // insert into BOOK (BOOK_ID, PAGE_COUNT, PRICE, PUBLISH_DATE, TITLE) values (null, ?, ?, ?, ?)
        // insert into BOOK (BOOK_ID, PAGE_COUNT, PRICE, PUBLISH_DATE, TITLE) values (null, ?, ?, ?, ?)
        // insert into BOOK (BOOK_ID, PAGE_COUNT, PRICE, PUBLISH_DATE, TITLE) values (null, ?, ?, ?, ?)
    }

    private static void read(){
        System.out.println("=========================");
        System.out.println("READ....");
        System.out.println("=========================");

        System.out.println("findOne....");
        final Book book = bookRepository.findOne(1L);
        // select * from BOOK b where b.BOOK_ID=?
        System.out.println(book);
        // Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)

        System.out.println("findAll....");
        final List<Book> books = bookRepository.findAll();
        // select * from BOOK b
        books.forEach(System.out::println);
        // Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
        // Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=100, price=15.00)
        // Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=100, price=13.00)
        // Book(bookId=4, title=War and Peace, publishDate=1955-11-08 00:00:00.0, pageCount=100, price=15.00)
        // Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-08 00:00:00.0, pageCount=100, price=16.00)
        // Book(bookId=6, title=Design Patterns, publishDate=1996-11-08 00:00:00.0, pageCount=100, price=14.00)
        // Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=100, price=15.00)

        System.out.println("findAll(Iterable ids)....");
        final List<Book> booksByIds = bookRepository.findAll(new ArrayList<Long>(){{
            add(1L);
            add(2L);
            add(3L);
        }});
        // select * from BOOK b where b.BOOK_ID in (? , ? , ?)
        booksByIds.forEach(System.out::println);
        // Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
        // Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=100, price=15.00)
        // Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=100, price=13.00)
    }

    private static void update(){
        System.out.println("=========================");
        System.out.println("UPDATE....");
        System.out.println("=========================");

        final Book book = bookRepository.findOne(1L);
        // select * from BOOK b where b.BOOK_ID=?
        book.setTitle("War and Peace");
        bookRepository.save(book);
        // update BOOK set PAGE_COUNT=?, PRICE=?, PUBLISH_DATE=?, TITLE=? where BOOK_ID=?

        System.out.println(book);
        // Book(bookId=1, title=War and Peace, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
    }

    private static void delete(){
        System.out.println("=========================");
        System.out.println("DELETE....");
        System.out.println("=========================");

        System.out.println("delete(id)....");
        bookRepository.delete(1L);
        // delete from BOOK where BOOK_ID=?

        System.out.println("delete(entities)....");
        bookRepository.delete(bookRepository.findAll(new ArrayList<Long>(){{
            add(4L);
            add(5L);
            add(6L);
        }}));
        // delete from BOOK where BOOK_ID=?
        // delete from BOOK where BOOK_ID=?
        // delete from BOOK where BOOK_ID=?

        System.out.println("deleteInBatch(entities)....");
        bookRepository.deleteInBatch(bookRepository.findAll(new ArrayList<Long>(){{
            add(7L);
            add(8L);
            add(9L);
        }}));
        // delete from BOOK where BOOK_ID=? or BOOK_ID=? or BOOK_ID=?

        System.out.println("deleteAll()....");
        bookRepository.deleteAll();
        // delete from BOOK where BOOK_ID=?
        // delete from BOOK where BOOK_ID=?
        // delete from BOOK where BOOK_ID=?
        // delete from BOOK where BOOK_ID=?

        System.out.println("deleteAllInBatch()....");
        bookRepository.deleteAllInBatch();
        // delete from BOOK
    }
}
