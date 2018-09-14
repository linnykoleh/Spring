package com.oreilly.sdata;

import java.util.List;

import com.oreilly.sdata.data.entities.Book;
import com.oreilly.sdata.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/application-context.xml")
@Slf4j
public class TestBookRepository {

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void queryExample() {
		log.info("===============");
		log.info("@Query example ");
		log.info("===============");

		final List<Book> books = bookRepository.queryAll();
		// select * from BOOK b
		// ...

		books.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=230, price=11.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))
		// ...

		final List<Book> books1 = bookRepository.queryAllFilter(300);
		// select * from BOOK b where b.PAGE_COUNT>?
		// select * from AUTHOR a where a.AUTHOR_ID=?
		// select * from AUTHOR a where a.AUTHOR_ID=?
		// select * from AUTHOR a where a.AUTHOR_ID=?

		books1.forEach(b -> log.info(b.toString()));
		// Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=301, price=15.00, author=Author(authorId=11, firstName=Ken, lastName=Kesey, country=Russia))
		// Book(bookId=4, title=War and Peace, publishDate=1955-11-21 00:00:00.0, pageCount=400, price=15.00, author=Author(authorId=10, firstName=Leo, lastName=Tolstoy, country=Russia))
		// Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-14 00:00:00.0, pageCount=350, price=16.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))

		final List<Book> books2 = bookRepository.queryAllFilterNamedParams("Design Patterns");
		// select * from BOOK b where b.TITLE>?
		// select * from AUTHOR a where a.AUTHOR_ID=?

		books2.forEach(b -> log.info(b.toString()));
		// Book(bookId=6, title=Design Patterns, publishDate=1996-11-25 00:00:00.0, pageCount=120, price=14.00, author=Author(authorId=9, firstName=Erich, lastName=Gamma, country=United States))
	}

	@Test
	public void namedQueryExample() {
		log.info("==========================");
		log.info("@Query(name = ... example ");
		log.info("==========================");

		final List<Book> books = bookRepository.queryAllNamedQuery();
		// select * from BOOK b
		// ...

		books.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=230, price=11.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))
		// ...

		final List<Book> books1 = bookRepository.queryAllFilterNamedQuery(300);
		// select * from BOOK b where b.PAGE_COUNT>?
		// select * from AUTHOR a where a.AUTHOR_ID=?
		// select * from AUTHOR a where a.AUTHOR_ID=?
		// select * from AUTHOR a where a.AUTHOR_ID=?

		books1.forEach(b -> log.info(b.toString()));
		// Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=301, price=15.00, author=Author(authorId=11, firstName=Ken, lastName=Kesey, country=Russia))
		// Book(bookId=4, title=War and Peace, publishDate=1955-11-21 00:00:00.0, pageCount=400, price=15.00, author=Author(authorId=10, firstName=Leo, lastName=Tolstoy, country=Russia))
		// Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-14 00:00:00.0, pageCount=350, price=16.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))

		final List<Book> books2 = bookRepository.queryAllFilterNamedParamsNamedQuery("Design Patterns");
		// select * from BOOK b where b.TITLE>?
		// select * from AUTHOR a where a.AUTHOR_ID=?

		books2.forEach(b -> log.info(b.toString()));
		// Book(bookId=6, title=Design Patterns, publishDate=1996-11-25 00:00:00.0, pageCount=120, price=14.00, author=Author(authorId=9, firstName=Erich, lastName=Gamma, country=United States))
	}

	@Test
	public void pagingExample() {
		log.info("===============");
		log.info("Paging example ");
		log.info("===============");

		final Pageable pageRequest = new PageRequest(0, 3);
		final Page<Book> books = bookRepository.findAll(pageRequest);
		// select count(b.BOOK_ID) from BOOK b
		// select top ? * from BOOK b

		books.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=230, price=11.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))
		// Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=301, price=15.00, author=Author(authorId=11, firstName=Ken, lastName=Kesey, country=Russia))
		// Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=210, price=13.00, author=Author(authorId=5, firstName=Ernest, lastName=Hemmingway, country=United States))

		final Pageable pageRequest1 = new PageRequest(1, 3);
		final Page<Book> books1 = bookRepository.findAll(pageRequest1);
		// select count(b.BOOK_ID) from BOOK b
		// select top ? * from BOOK b

		books1.forEach(b -> log.info(b.toString()));
		// Book(bookId=4, title=War and Peace, publishDate=1955-11-21 00:00:00.0, pageCount=400, price=15.00, author=Author(authorId=10, firstName=Leo, lastName=Tolstoy, country=Russia))
		// Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-14 00:00:00.0, pageCount=350, price=16.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))
		// Book(bookId=6, title=Design Patterns, publishDate=1996-11-25 00:00:00.0, pageCount=120, price=14.00, author=Author(authorId=9, firstName=Erich, lastName=Gamma, country=United States))

		final List<Book> books2 = bookRepository.findByPageCountGreaterThan(300, new PageRequest(0, 2));
		// select top ? * from BOOK b where b.PAGE_COUNT>?

		books2.forEach(b -> log.info(b.toString()));
		// Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=301, price=15.00, author=Author(authorId=11, firstName=Ken, lastName=Kesey, country=Russia))
		// Book(bookId=4, title=War and Peace, publishDate=1955-11-21 00:00:00.0, pageCount=400, price=15.00, author=Author(authorId=10, firstName=Leo, lastName=Tolstoy, country=Russia))
	}

	@Test
	public void pagingAndSortingExample() {
		log.info("===========================");
		log.info("Paging and Sorting example ");
		log.info("===========================");

		final Pageable pageRequest = new PageRequest(0, 3, new Sort(Sort.Direction.ASC, "price"));
		final Page<Book> books = bookRepository.findAll(pageRequest);
		// select count(b.BOOK_ID) from BOOK b
		// select top ? * from BOOK b order by b.PRICE asc

		books.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=230, price=11.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))
		// Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=210, price=13.00, author=Author(authorId=5, firstName=Ernest, lastName=Hemmingway, country=United States))
		// Book(bookId=10, title=Great Expectations, publishDate=1878-10-08 00:00:00.0, pageCount=140, price=13.66, author=Author(authorId=6, firstName=Charles, lastName=Dickens, country=England))
	}

	@Test
	public void sortingExample() {
		log.info("================");
		log.info("Sorting example ");
		log.info("================");

		final Sort sort = new Sort(Sort.Direction.ASC, "price");
		final List<Book> books = bookRepository.findAll(sort);
		// select * from BOOK b order by b.PRICE asc

		books.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=230, price=11.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))
		// Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=210, price=13.00, author=Author(authorId=5, firstName=Ernest, lastName=Hemmingway, country=United States))
		// Book(bookId=10, title=Great Expectations, publishDate=1878-10-08 00:00:00.0, pageCount=140, price=13.66, author=Author(authorId=6, firstName=Charles, lastName=Dickens, country=England))
		// Book(bookId=6, title=Design Patterns, publishDate=1996-11-25 00:00:00.0, pageCount=120, price=14.00, author=Author(authorId=9, firstName=Erich, lastName=Gamma, country=United States))
		// Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=301, price=15.00, author=Author(authorId=11, firstName=Ken, lastName=Kesey, country=Russia))
		// Book(bookId=4, title=War and Peace, publishDate=1955-11-21 00:00:00.0, pageCount=400, price=15.00, author=Author(authorId=10, firstName=Leo, lastName=Tolstoy, country=Russia))

		final Sort sort1 = new Sort(Sort.Direction.ASC, "price", "author.lastName", "pageCount");
		final List<Book> books1 = bookRepository.findAll(sort1);
		// select * from BOOK   b left outer join AUTHOR a on b.AUTHOR_ID=a.AUTHOR_ID order by   b.PRICE asc, a.LAST_NAME asc, b.PAGE_COUNT asc

		books1.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=230, price=11.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))
		// Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=210, price=13.00, author=Author(authorId=5, firstName=Ernest, lastName=Hemmingway, country=United States))
		// Book(bookId=10, title=Great Expectations, publishDate=1878-10-08 00:00:00.0, pageCount=140, price=13.66, author=Author(authorId=6, firstName=Charles, lastName=Dickens, country=England))
		// Book(bookId=6, title=Design Patterns, publishDate=1996-11-25 00:00:00.0, pageCount=120, price=14.00, author=Author(authorId=9, firstName=Erich, lastName=Gamma, country=United States))
		// Book(bookId=7, title=A Tale of Two Cities, publishDate=1943-11-19 00:00:00.0, pageCount=270, price=15.00, author=Author(authorId=6, firstName=Charles, lastName=Dickens, country=England))

		final Sort sort2 = new Sort(Sort.Direction.ASC, "price").and(new Sort(Sort.Direction.DESC, "pageCount"));
		final List<Book> books2 = bookRepository.findAll(sort2);
		// select * from BOOK  b order by b.PRICE asc, b.PAGE_COUNT desc

		books2.forEach(b -> log.info(b.toString()));
		// Book(bookId=1, title=Of Mice and Men, publishDate=1954-11-08 00:00:00.0, pageCount=230, price=11.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))
		// Book(bookId=3, title=For Whom the Bell Tolls, publishDate=1932-11-08 00:00:00.0, pageCount=210, price=13.00, author=Author(authorId=5, firstName=Ernest, lastName=Hemmingway, country=United States))
		// Book(bookId=10, title=Great Expectations, publishDate=1878-10-08 00:00:00.0, pageCount=140, price=13.66, author=Author(authorId=6, firstName=Charles, lastName=Dickens, country=England))
		// Book(bookId=6, title=Design Patterns, publishDate=1996-11-25 00:00:00.0, pageCount=120, price=14.00, author=Author(authorId=9, firstName=Erich, lastName=Gamma, country=United States))
		// Book(bookId=4, title=War and Peace, publishDate=1955-11-21 00:00:00.0, pageCount=400, price=15.00, author=Author(authorId=10, firstName=Leo, lastName=Tolstoy, country=Russia))
		// Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=301, price=15.00, author=Author(authorId=11, firstName=Ken, lastName=Kesey, country=Russia))

		final List<Book> books3 = bookRepository.findByPageCountGreaterThan(300, new Sort(Sort.Direction.DESC, "pageCount"));
		// select * from BOOK b where b.PAGE_COUNT>? order by b.PAGE_COUNT desc

		books3.forEach(b -> log.info(b.toString()));
		// Book(bookId=4, title=War and Peace, publishDate=1955-11-21 00:00:00.0, pageCount=400, price=15.00, author=Author(authorId=10, firstName=Leo, lastName=Tolstoy, country=Russia))
		// Book(bookId=5, title=The Grapes of Wrath, publishDate=1955-11-14 00:00:00.0, pageCount=350, price=16.00, author=Author(authorId=1, firstName=John, lastName=Steinbeck, country=United States))
		// Book(bookId=2, title=One Flew Over the Cuckoos Nest, publishDate=1973-11-08 00:00:00.0, pageCount=301, price=15.00, author=Author(authorId=11, firstName=Ken, lastName=Kesey, country=Russia))
	}
}
