package com.guitar.db.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guitar.db.model.Manufacturer;
import com.guitar.db.repository.spring_data.ManufacturerDataJPARepository;

@Repository
public class ManufacturerRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ManufacturerDataJPARepository manufacturerDataJPARepository;

	public Manufacturer create(Manufacturer man) {
		manufacturerDataJPARepository.saveAndFlush(man);
		return man;
	}

	public Manufacturer find(Long id) {
		return manufacturerDataJPARepository.findOne(id);
	}

	public Manufacturer update(Manufacturer man) {
		manufacturerDataJPARepository.saveAndFlush(man);
		return man;
	}

	public void delete(Manufacturer man) {
		manufacturerDataJPARepository.delete(man);
	}

	/**
	 * Custom finder
	 */
	public List<Manufacturer> getManufacturersFoundedBeforeDate(Date date) {
		List<Manufacturer> mans = entityManager
				.createQuery("select m from Manufacturer m where m.foundedDate < :date")
				.setParameter("date", date).getResultList();
		return mans;
	}

	/**
	 * Custom finder
	 */
	public Manufacturer getManufacturerByName(String name) {
		Manufacturer man = (Manufacturer)entityManager
				.createQuery("select m from Manufacturer m where m.name like :name")
				.setParameter("name", name + "%").getSingleResult();
		return man;
	}

	/**
	 * Native Query finder
	 */
	public List<Manufacturer> getManufacturersThatSellModelsOfType(String modelType) {
		List<Manufacturer> mans = entityManager
				.createNamedQuery("Manufacturer.getAllThatSellAcoustics")
				.setParameter(1, modelType).getResultList();
		return mans;
	}
}
