package com.oreilly.sdata.repositories;

import java.util.Date;
import java.util.List;

import com.oreilly.sdata.data.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

	// String operators
	Book findByTitle(String title);

	List<Book> findByTitleLike(String title);

	List<Book> findByTitleContaining(String title);

	List<Book> findByTitleStartingWith(String title);

	List<Book> findByTitleEndingWith(String title);

	List<Book> findByTitleLikeIgnoreCase(String title);
	//--------------------------


	// Relationship operators
	List<Book> findByPageCountEquals(int pageCount);

	List<Book> findByPageCountGreaterThan(int pageCount);

	List<Book> findByPageCountLessThan(int pageCount);

	List<Book> findByPageCountGreaterThanEqual(int pageCount);

	List<Book> findByPageCountLessThanEqual(int pageCount);

	List<Book> findByPageCountBetween(int min, int max);
	//--------------------------


	// Logical operators
	List<Book> findByTitleContainingOrTitleContaining(String title1, String title2);

	List<Book> findByTitleContainingOrPageCountGreaterThan(String title, int pageCount);

	List<Book> findByTitleNot(String title);
	//--------------------------


	// Date comparisons
	List<Book> findByPublishDateAfter(Date date);

	List<Book> findByPublishDateBefore(Date date);

	List<Book> findByPublishDateBetween(Date date1, Date date2);
	//---------------------------


	// Ordering result
	List<Book> findByTitleContainingOrderByTitleAsc(String title);

	List<Book> findByTitleContainingOrderByTitleDesc(String title);
	//---------------------------


	// Limiting query result
	List<Book> queryTopByOrderByPageCountDesc();

	List<Book> queryFirstByOrderByPageCountAsc();

	List<Book> queryTop3ByOrderByPriceAsc();

	List<Book> queryTop3ByTitleLikeOrderByPriceAsc(String title);
	//---------------------------



	// Traversing Nested Properties

	List<Book> findByAuthorFirstName(String firstName);

	List<Book> findByAuthorCountry(String country);
	//---------------------------

}
