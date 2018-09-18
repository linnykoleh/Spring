package com.oreilly.sdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oreilly.sdata.data.entities.Book;
import com.oreilly.sdata.repository.BookRepository;
import com.oreilly.sdata.util.BookUtil;

import lombok.extern.slf4j.Slf4j;

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
}
