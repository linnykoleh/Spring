package com.oreilly.sdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository repo;

	public void save(Book book){
		this.repo.save(book);
	}
}
