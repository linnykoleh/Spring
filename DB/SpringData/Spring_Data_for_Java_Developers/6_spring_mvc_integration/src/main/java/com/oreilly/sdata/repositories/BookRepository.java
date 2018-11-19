package com.oreilly.sdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oreilly.sdata.data.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
