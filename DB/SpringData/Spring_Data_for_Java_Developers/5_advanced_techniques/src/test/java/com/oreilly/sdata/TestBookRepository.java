package com.oreilly.sdata;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oreilly.sdata.data.entities.Book;
import com.oreilly.sdata.repositories.BookRepository;
import com.oreilly.sdata.util.BookUtil;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/application-context.xml")
@Slf4j
public class TestBookRepository {

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void testCustomSaving() {
		final Book actual = BookUtil.create();
		bookRepository.saveAndLog(actual);
		// insert into BOOK (BOOK_ID, AUTHOR_ID, PAGE_COUNT, PRICE, PUBLISH_DATE, TITLE) values (null, ?, ?, ?, ?, ?)

		final Book expected = bookRepository.findOne(actual.getBookId());
		// select * from BOOK b left outer join AUTHOR a on b.AUTHOR_ID=a.AUTHOR_ID where b.BOOK_ID=?

		Assert.assertEquals(expected, actual);
	}

}
