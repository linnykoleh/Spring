package com.oreilly.sdata;

import com.oreilly.sdata.data.entities.Book;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Application {

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DataConfiguration.class);
        final BookService bean = context.getBean(BookService.class);

        final Book book = new Book();
        book.setTitle("A test book");
        book.setPageCount(200);
        book.setPublishDate(new Date());

        bean.save(book);
    }
}
