package com.oreilly.sdata.repositories;

import com.oreilly.sdata.data.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
