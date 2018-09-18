package com.oreilly.sdata.repository;

import com.oreilly.sdata.Book;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    Book findByTitle(String title);

    List<Book> findAllBy(TextCriteria textCriteria);

}
