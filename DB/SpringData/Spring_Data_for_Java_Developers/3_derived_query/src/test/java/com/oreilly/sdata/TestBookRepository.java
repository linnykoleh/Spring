package com.oreilly.sdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oreilly.sdata.data.entities.Book;
import com.oreilly.sdata.repositories.BookRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/application-context.xml")
@Slf4j
public class TestBookRepository {

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void stringOperatorsExample(){
		log.info("========================");
		log.info("String operators example");
		log.info("========================");

		final Book book = bookRepository.findByTitle("Of Mice and Men");
		// select * from BOOK b where b.TITLE=?
		log.info(book.toString());
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)

		final List<Book> books = bookRepository.findByTitleLike("%of%");
		// select * from BOOK b where b.TITLE like ?
		books.forEach(b -> log.info(b.toString()));
		// Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-08 00:00:00.0, pageCount=100, price=16.00)
		// Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=100, price=15.00)

		final List<Book> books1 = bookRepository.findByTitleStartingWith("O");
		// select * from BOOK b where b.TITLE like ?
		books1.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
		// Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=100, price=15.00)

		final List<Book> books2 = bookRepository.findByTitleEndingWith("s");
		// select * from BOOK b where b.TITLE like ?
		books2.forEach(b -> log.info(b.toString()));
		// Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=100, price=13.00)
		// Book(bookId=6, title=Design Patterns, publishDate=1996-11-08 00:00:00.0, pageCount=100, price=14.00)
		// Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=100, price=15.00)

		final List<Book> books3 = bookRepository.findByTitleLikeIgnoreCase("%OF%");
		// select * from BOOK b where upper(b.TITLE) like upper(?)
		books3.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
		// Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-08 00:00:00.0, pageCount=100, price=16.00)
		// Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=100, price=15.00)
	}

	@Test
	public void relationshipOperatorsExample() {
		log.info("==============================");
		log.info("Relationship operators example");
		log.info("==============================");

		final List<Book> books = bookRepository.findByPageCountEquals(3000);
		// select * from BOOK b where b.PAGE_COUNT=?
		books.forEach(b -> log.info(b.toString()));
		// Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=3000, price=15.00)

		final List<Book> books1 = bookRepository.findByPageCountLessThan(100);
		// select * from BOOK b where b.PAGE_COUNT<?
		books1.forEach(b -> log.info(b.toString()));
		// Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-08 00:00:00.0, pageCount=80, price=16.00)
		// Book(bookId=6, title=Design Patterns, publishDate=1996-11-08 00:00:00.0, pageCount=90, price=14.00)

		final List<Book> books2 = bookRepository.findByPageCountBetween(100, 1000);
		// select * from BOOK b where b.PAGE_COUNT between ? and ?
		books2.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
		// Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=100, price=15.00)
		// Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=100, price=13.00)
		// Book(bookId=4, title=War and Peace, publishDate=1955-11-08 00:00:00.0, pageCount=100, price=15.00)
	}

	@Test
	public void logicalOperatorsExample() {
		log.info("=========================");
		log.info("Logical operators example");
		log.info("=========================");

		final List<Book> books = bookRepository.findByTitleContainingOrPageCountGreaterThan("of", 100);
		// select * from BOOK b where b.TITLE like ? or b.PAGE_COUNT>?
		books.forEach(b -> log.info(b.toString()));
		// Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-08 00:00:00.0, pageCount=80, price=16.00)
		// Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=3000, price=15.00)

		final List<Book> books1 = bookRepository.findByTitleContainingOrTitleContaining("of", "and");
		// select * from BOOK b where b.TITLE like ? or b.TITLE like ?
		books1.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
		// Book(bookId=4, title=War and Peace, publishDate=1955-11-08 00:00:00.0, pageCount=100, price=15.00)
		// Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-08 00:00:00.0, pageCount=80, price=16.00)
		// Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=3000, price=15.00)

		final List<Book> books2 = bookRepository.findByTitleNot("animal");
		// select * from BOOK b where b.TITLE<>?
		books2.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
		// Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=100, price=15.00)
		// Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=100, price=13.00)
		// Book(bookId=4, title=War and Peace, publishDate=1955-11-08 00:00:00.0, pageCount=100, price=15.00)
		// Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-08 00:00:00.0, pageCount=80, price=16.00)
		// Book(bookId=6, title=Design Patterns, publishDate=1996-11-08 00:00:00.0, pageCount=90, price=14.00)
		// Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=3000, price=15.00)
	}

	@Test
	public void dateComparisons() throws ParseException {
		log.info("========================");
		log.info("Date Comparisons example");
		log.info("========================");

		final Date date = new SimpleDateFormat("MM/dd/yyyy").parse("10/22/1995");
		final Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse("10/22/1997");

		final List<Book> books = bookRepository.findByPublishDateAfter(date);
		// select * from BOOK b where b.PUBLISH_DATE>?

		books.forEach(b -> log.info(b.toString()));
		// Book(bookId=6, title=Design Patterns, publishDate=1996-11-08 00:00:00.0, pageCount=90, price=14.00)

		final List<Book> books1 = bookRepository.findByPublishDateBefore(date);
		// select * from BOOK b where b.PUBLISH_DATE<?

		books1.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
		// Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=100, price=15.00)
		// Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=100, price=13.00)
		// Book(bookId=4, title=War and Peace, publishDate=1955-11-08 00:00:00.0, pageCount=100, price=15.00)
		// Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-08 00:00:00.0, pageCount=80, price=16.00)
		// Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=3000, price=15.00)

		final List<Book> books2 = bookRepository.findByPublishDateBetween(date, date1);
		// select * from BOOK b where b.PUBLISH_DATE between ? and ?

		books2.forEach(b -> log.info(b.toString()));
		// Book(bookId=6, title=Design Patterns, publishDate=1996-11-08 00:00:00.0, pageCount=90, price=14.00)
	}

	@Test
	public void orderingResult() {
		log.info("=======================");
		log.info("Ordering Result example");
		log.info("=======================");

		final List<Book> books = bookRepository.findByTitleContainingOrderByTitleAsc("Of");
		// select * from BOOK b where b.TITLE like ? order by b.TITLE asc

		books.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)

		final List<Book> books1 = bookRepository.findByTitleContainingOrderByTitleDesc("Of");
		// select * from BOOK b where b.TITLE like ? order by b.TITLE desc

		books1.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
	}

	@Test
	public void limitingQueryResultExample() {
		log.info("==============================");
		log.info("Limiting Query Result example");
		log.info("==============================");

		final List<Book> books = bookRepository.queryTopByOrderByPageCountDesc();
		// select top ? * from BOOK b order by b.PAGE_COUNT desc

		books.forEach(b -> log.info(b.toString()));
		// Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-08 00:00:00.0, pageCount=3000, price=15.00)

		final List<Book> books1 = bookRepository.queryTop3ByOrderByPriceAsc();
		// select top ? * from BOOK b order by b.PRICE asc

		books1.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
		// Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=100, price=13.00)
		// Book(bookId=6, title=Design Patterns, publishDate=1996-11-08 00:00:00.0, pageCount=90, price=14.00)

		final List<Book> books2 = bookRepository.queryTop3ByTitleLikeOrderByPriceAsc("%a%");
		// select top ? * from BOOK book0_ where book0_.TITLE like ? order by book0_.PRICE asc

		books2.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=100, price=11.00)
		// Book(bookId=6, title=Design Patterns, publishDate=1996-11-08 00:00:00.0, pageCount=90, price=14.00)
		// Book(bookId=4, title=War and Peace, publishDate=1955-11-08 00:00:00.0, pageCount=100, price=15.00)
	}

	@Test
	public void traversingNestedProperties() {
		log.info("====================================");
		log.info("Traversing Nested Properties example");
		log.info("====================================");

		final List<Book> books = bookRepository.findByAuthorFirstName("John");
		// select * from BOOK b left outer join AUTHOR A on b.AUTHOR_ID=A.AUTHOR_ID where A.FIRST_NAME=?
		// select * from AUTHOR author0_ where author0_.AUTHOR_ID=?

		books.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=230, price=11.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))
		// Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-14 00:00:00.0, pageCount=350, price=16.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))

		final List<Book> books1 = bookRepository.findByAuthorCountry("United States");
		// select * from BOOK b left outer join AUTHOR a on b.AUTHOR_ID=a.AUTHOR_ID where a.COUNTRY=?
		// select * from AUTHOR a where a.AUTHOR_ID=?
		// select * from AUTHOR a where a.AUTHOR_ID=?
		// select * from AUTHOR a where a.AUTHOR_ID=?
		// select * from AUTHOR a where a.AUTHOR_ID=?
		// select * from AUTHOR a where a.AUTHOR_ID=?

		books1.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=230, price=11.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))
		// Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=210, price=13.00, author=Author(authorId=5, firstName=Ernest, lastName=Hemmingway, country=United States))
		// Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-14 00:00:00.0, pageCount=350, price=16.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))
		// Book(bookId=6, title=Design Patterns, publishDate=1996-11-25 00:00:00.0, pageCount=120, price=14.00, author=Author(authorId=9, firstName=Erich, lastName=Gamma, country=United States))
		// Book(bookId=8, title=Animal Farm, publishDate=1965-06-08 00:00:00.0, pageCount=126, price=22.00, author=Author(authorId=2, firstName=George, lastName=Orwell, country=United States))
		// Book(bookId=12, title=The Scarlet Letter, publishDate=1957-10-08 00:00:00.0, pageCount=170, price=16.00, author=Author(authorId=8, firstName=Nathaniel, lastName=Hawthorne, country=United States))
		// Book(bookId=14, title=The Old Man and the Sea, publishDate=1952-09-08 00:00:00.0, pageCount=190, price=23.00, author=Author(authorId=5, firstName=Ernest, lastName=Hemmingway, country=United States))

	}
}
