package com.oreilly.sdata.repositories.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oreilly.sdata.data.entities.Book;

import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@Slf4j
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveAndLog(Book book) {
		log.info("Saving the book entity: {} ", book);
		entityManager.persist(book);
	}
}
