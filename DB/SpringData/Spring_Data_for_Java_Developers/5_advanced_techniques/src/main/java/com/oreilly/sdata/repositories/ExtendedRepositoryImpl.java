package com.oreilly.sdata.repositories;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ExtendedRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements BaseRepository<T, ID> {

	private JpaEntityInformation<T, ?> entityInformation;
	private EntityManager entityManager;

	public ExtendedRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.entityManager = entityManager;
	}

	@Override
	public List<T> findByIds(ID... ids) {
		final Query query = entityManager.createQuery(
				" select e from "
						+ entityInformation.getEntityName()
						+ " e where e."
						+ entityInformation.getIdAttribute().getName()
						+ " in :ids"
		);
		query.setParameter("ids", Arrays.asList(ids));
		return query.getResultList();
	}

	@Override
	public List<T> findByIdsAsync(ID... ids) {
		final Query query = entityManager.createQuery(
				" select e from "
						+ entityInformation.getEntityName()
						+ " e where e."
						+ entityInformation.getIdAttribute().getName()
						+ " in :ids"
		);
		query.setParameter("ids", Arrays.asList(ids));

		long wait = new Random().nextInt(10000 - 1) + 1;
		System.out.println(wait);

		try {
			Thread.sleep(wait);
		}
		catch (InterruptedException e) {
		}

		System.out.println("Execution for ids: " + Arrays.toString(ids) + "Thread: " + Thread.currentThread().getName());

		return query.getResultList();
	}
}
