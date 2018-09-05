package com.guitar.db.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.guitar.db.model.ModelType;

@Repository
public class ModelTypeRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public ModelType create(ModelType mt) {
		entityManager.persist(mt);
		entityManager.flush();
		return mt;
	}

	public ModelType find(Long id) {
		return entityManager.find(ModelType.class, id);
	}

	public ModelType update(ModelType mt) {
		mt = entityManager.merge(mt);
		entityManager.flush();
		return mt;
	}

	public void delete(ModelType mt) {
		entityManager.remove(mt);
		entityManager.flush();
	}


}
