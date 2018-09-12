package com.oreilly.sdata;

import com.oreilly.sdata.data.entities.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;

@SpringBootApplication
public class SpringDataBootApplication {

    public static void main(String[] args) {
        final ApplicationContext context = SpringApplication.run(SpringDataBootApplication.class, args);
        final BookRepository repository = context.getBean(BookRepository.class);

        final Book book = new Book();
        book.setTitle("A test book");
        book.setPageCount(200);
        book.setPublishDate(new Date());

        repository.save(book);
    }
}
