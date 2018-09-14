package com.oreilly.sdata.repositories;

import java.util.List;


import com.oreilly.sdata.data.entities.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

	/* @Query */
	@Query("select b from Book b")
	List<Book> queryAll();

	@Query("select b from Book b where b.pageCount > ?1")
	List<Book> queryAllFilter(int pageCount);

	@Query("select b from Book b where b.title > :title")
	List<Book> queryAllFilterNamedParams(@Param("title") String title);


	/* @NamedQuery */
	@Query(name = "Book.queryOne")
	List<Book> queryAllNamedQuery();

	@Query(name = "Book.queryTwo")
	List<Book> queryAllFilterNamedQuery(int pageCount);

	@Query(name = "Book.queryThree")
	List<Book> queryAllFilterNamedParamsNamedQuery(@Param("title") String title);

	/* Paging */
	List<Book> findByPageCountGreaterThan(int pageCount, Pageable pageable);

	Page<Book> findByPageCountLessThan(int pageCount, Pageable pageable);

	Slice<Book> findByPageCountBetween(int pageCount1, int pageCount2, Pageable pageable);


	/* Sorting */
	List<Book> findByPageCountGreaterThan(int pageCount, Sort sort);

}
