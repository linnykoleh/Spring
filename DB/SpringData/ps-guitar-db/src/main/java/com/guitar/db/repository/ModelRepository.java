package com.guitar.db.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guitar.db.model.Model;
import com.guitar.db.repository.spring_data.ModelDataJPARepository;

@Repository
public class ModelRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ModelDataJPARepository modelDataJPARepository;

	public Model create(Model mod) {
		modelDataJPARepository.saveAndFlush(mod);
		return mod;
	}

	public Model find(Long id) {
		return modelDataJPARepository.findOne(id);
	}

	public Model update(Model mod) {
		modelDataJPARepository.saveAndFlush(mod);
		return mod;
	}

	public void delete(Model mod) {
		modelDataJPARepository.delete(mod);
	}

	/**
	 * Custom finder
	 */
	public List<Model> getModelsInPriceRange(BigDecimal lowest, BigDecimal highest) {
		List<Model> mods = entityManager
				.createQuery("select m from Model m where m.price >= :lowest and m.price <= :highest")
				.setParameter("lowest", lowest)
				.setParameter("highest", highest).getResultList();
		return mods;
	}

	/**
	 * Custom finder
	 */
	public List<Model> getModelsByPriceRangeAndWoodType(BigDecimal lowest, BigDecimal highest, String wood) {
		List<Model> mods = entityManager
				.createQuery("select m from Model m where m.price >= :lowest and m.price <= :highest and m.woodType like :wood")
				.setParameter("lowest", lowest)
				.setParameter("highest", highest)
				.setParameter("wood", "%" + wood + "%").getResultList();
		return mods;
	}

	/**
	 * NamedQuery finder
	 */
	public List<Model> getModelsByType(String modelType) {
		List<Model> mods = entityManager
				.createNamedQuery("Model.findAllModelsByType")
				.setParameter("name", modelType).getResultList();
		return mods;
	}

	/**
	 * Count
	 */
	public Long getModelCount() {
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Model.class)));
		return entityManager.createQuery(cq).getSingleResult();
	}
}
