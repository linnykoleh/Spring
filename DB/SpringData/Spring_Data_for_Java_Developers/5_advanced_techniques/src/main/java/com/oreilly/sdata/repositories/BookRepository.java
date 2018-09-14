package com.oreilly.sdata.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oreilly.sdata.data.entities.Book;
import com.oreilly.sdata.repositories.custom.BookRepositoryCustom;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

	List<Book> findByPageCountGreaterThan(int pageCount, Sort sort);
}
