package com.oreilly.sdata;

import com.oreilly.sdata.data.entities.Book;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Application {

    public static void main(String[] args) {
        final ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/application-context.xml");

        final BookService bean = context.getBean(BookService.class);

        final Book book = new Book();
        book.setTitle("A test book");
        book.setPageCount(200);
        book.setPublishDate(new Date());

        bean.save(book);
    }
}
