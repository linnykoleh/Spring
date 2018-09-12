package com.oreilly.sdata;

import com.oreilly.sdata.data.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    void save(final Book book){
        this.bookRepository.save(book);
    }
}
