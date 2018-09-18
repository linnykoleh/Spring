package com.oreilly.sdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oreilly.sdata.data.entities.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
