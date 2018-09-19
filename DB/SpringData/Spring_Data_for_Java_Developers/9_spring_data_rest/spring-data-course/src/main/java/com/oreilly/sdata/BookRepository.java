package com.oreilly.sdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "classics", collectionResourceRel = "classics")
public interface BookRepository extends JpaRepository<Book, Long> {

	@RestResource(path = "title", rel = "title", exported = false)
	List<Book> findByTitle(@Param("title") String title);

	List<Book> findByPageCount(int pageCount);
}
