package com.oreilly.sdata;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oreilly.sdata.data.entities.Book;
import com.oreilly.sdata.repositories.BookRepository;

public class Application {

    private static BookRepository bookRepository;

    public static void main(String[] args) {
        final ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/application-context.xml");
        bookRepository = context.getBean(BookRepository.class);

        stringOperatorsExample();
        relationshipOperatorsExample();
        logicalOperatorsExample();
    }

    private static void stringOperatorsExample(){
        System.out.println("========================");
        System.out.println("String operators example");
        System.out.println("========================");

        final Book book = bookRepository.findByTitle("Of Mice and Men");
        // select * from BOOK b where b.TITLE=?
        System.out.println(book);
        // Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)

        final List<Book> books = bookRepository.findByTitleLike("%of%");
        // select * from BOOK b where b.TITLE like ?
        books.forEach(System.out::println);
        // Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-08 00:00:00.0, pageCount=100, price=16.00)
        // Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=100, price=15.00)

        final List<Book> books1 = bookRepository.findByTitleStartingWith("O");
        // select * from BOOK b where b.TITLE like ?
        books1.forEach(System.out::println);
        // Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
        // Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=100, price=15.00)

        final List<Book> books2 = bookRepository.findByTitleEndingWith("s");
        // select * from BOOK b where b.TITLE like ?
        books2.forEach(System.out::println);
        // Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=100, price=13.00)
        // Book(bookId=6, title=Design Patterns, publishDate=1996-11-08 00:00:00.0, pageCount=100, price=14.00)
        // Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=100, price=15.00)

        final List<Book> books3 = bookRepository.findByTitleLikeIgnoreCase("%OF%");
        // select * from BOOK b where upper(b.TITLE) like upper(?)
        books3.forEach(System.out::println);
        // Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
        // Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-08 00:00:00.0, pageCount=100, price=16.00)
        // Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=100, price=15.00)
    }

    private static void relationshipOperatorsExample() {
        System.out.println("==============================");
        System.out.println("Relationship operators example");
        System.out.println("==============================");

        final List<Book> books = bookRepository.findByPageCountEquals(3000);
        // select * from BOOK b where b.PAGE_COUNT=?
        books.forEach(System.out::println);
        // Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=3000, price=15.00)

        final List<Book> books1 = bookRepository.findByPageCountLessThan(100);
        // select * from BOOK b where b.PAGE_COUNT<?
        books1.forEach(System.out::println);
        // Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-08 00:00:00.0, pageCount=80, price=16.00)
        // Book(bookId=6, title=Design Patterns, publishDate=1996-11-08 00:00:00.0, pageCount=90, price=14.00)

        final List<Book> books2 = bookRepository.findByPageCountBetween(100, 1000);
        // select * from BOOK b where b.PAGE_COUNT between ? and ?
        books2.forEach(System.out::println);
        // Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
        // Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=100, price=15.00)
        // Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=100, price=13.00)
        // Book(bookId=4, title=War and Peace, publishDate=1955-11-08 00:00:00.0, pageCount=100, price=15.00)
    }

    private static void logicalOperatorsExample() {
        System.out.println("=========================");
        System.out.println("Logical operators example");
        System.out.println("=========================");

        final List<Book> books = bookRepository.findByTitleContainingOrPageCountGreaterThan("of", 100);
        // select * from BOOK b where b.TITLE like ? or b.PAGE_COUNT>?
        books.forEach(System.out::println);
        // Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-08 00:00:00.0, pageCount=80, price=16.00)
        // Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=3000, price=15.00)

        final List<Book> books1 = bookRepository.findByTitleContainingOrTitleContaining("of", "and");
        // select * from BOOK b where b.TITLE like ? or b.TITLE like ?
        books1.forEach(System.out::println);
        // Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
        // Book(bookId=4, title=War and Peace, publishDate=1955-11-08 00:00:00.0, pageCount=100, price=15.00)
        // Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-08 00:00:00.0, pageCount=80, price=16.00)
        // Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=3000, price=15.00)

        final List<Book> books2 = bookRepository.findByTitleNot("animal");
        // select * from BOOK b where bobok0_.TITLE<>?
        books2.forEach(System.out::println);
        // Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
        // Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=100, price=15.00)
        // Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=100, price=13.00)
        // Book(bookId=4, title=War and Peace, publishDate=1955-11-08 00:00:00.0, pageCount=100, price=15.00)
        // Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-08 00:00:00.0, pageCount=80, price=16.00)
        // Book(bookId=6, title=Design Patterns, publishDate=1996-11-08 00:00:00.0, pageCount=90, price=14.00)
        // Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=3000, price=15.00)
    }


}
