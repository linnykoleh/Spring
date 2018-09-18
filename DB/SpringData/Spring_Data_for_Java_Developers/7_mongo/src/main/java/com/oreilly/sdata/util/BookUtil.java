package com.oreilly.sdata.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.oreilly.sdata.data.entities.Book;

public class BookUtil {

    private static String[] titles = { "Don Quixote", "1984", "Adventures of Huckleberry Finn", "Ulysses",
            "The Great Gatsby", "On The Road", "Catch 22", "To Kill A Mockingbird", "Brave New World",
            "The Scarlet Letter" };

    public static List<Book> create(int size) {
        final List<Book> books = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            books.add(BookUtil.create());
        }
        return books;
    }

    public static Book create() {
        final Book book = new Book();
        book.setTitle(titles[ThreadLocalRandom.current().nextInt(1, titles.length)]);
        book.setPageCount(ThreadLocalRandom.current().nextInt(100, 151));
        book.setPublishDate(new Date());
        book.setPrice(new BigDecimal("15.00"));
        return book;
    }

}
