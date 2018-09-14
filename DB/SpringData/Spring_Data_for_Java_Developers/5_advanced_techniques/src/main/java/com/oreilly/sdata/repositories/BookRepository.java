package com.oreilly.sdata.repositories;

import com.oreilly.sdata.data.entities.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends BaseRepository<Book, Long> {

	List<Book> findByPageCountGreaterThan(int pageCount, Sort sort);

	@Transactional
	@Modifying
	@Query("update Book b set b.pageCount = ?1 where b.title like ?2")
	int setPageCount(int pageCount, String title);
}
