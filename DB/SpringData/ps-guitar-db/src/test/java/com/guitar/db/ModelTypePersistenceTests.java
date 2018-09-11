package com.guitar.db;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.guitar.db.model.ModelType;
import com.guitar.db.repository.ModelTypeRepository;
import com.guitar.db.repository.spring_data.ModelTypeDataJPARepository;

import java.util.List;

@ContextConfiguration(locations={"classpath:com/guitar/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ModelTypePersistenceTests {

	@Autowired
	private ModelTypeRepository modelTypeRepository;

	@Autowired
	private ModelTypeDataJPARepository modelTypeDataJPARepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() {
		ModelType mt = new ModelType();
		mt.setName("Test Model Type");
		mt = modelTypeRepository.create(mt);

		// clear the persistence context so we don't return the previously cached location object
		// this is a test only thing and normally doesn't need to be done in prod code
		entityManager.clear();

		ModelType otherModelType = modelTypeRepository.find(mt.getId());
		assertEquals("Test Model Type", otherModelType.getName());

		modelTypeRepository.delete(otherModelType);
	}

	@Test
	@Transactional
	public void testSaveAndGetAndDeleteUsingSpringData() {
		ModelType mt = new ModelType();
		mt.setName("Test Model Type");
		mt = modelTypeDataJPARepository.save(mt);

		// clear the persistence context so we don't return the previously cached location object
		// this is a test only thing and normally doesn't need to be done in prod code
		entityManager.clear();

		ModelType otherModelType = modelTypeDataJPARepository.findOne(mt.getId());
		assertEquals("Test Model Type", otherModelType.getName());

		modelTypeDataJPARepository.delete(otherModelType);
	}

	@Test
	public void testFind() {
		ModelType mt = modelTypeRepository.find(1L);
		assertEquals("Dreadnought Acoustic", mt.getName());
	}

	@Test
	public void testFind_SpringData() {
		ModelType mt = modelTypeDataJPARepository.findOne(1L);
		assertEquals("Dreadnought Acoustic", mt.getName());
	}

	@Test
	public void testFindByNameIsNull_SpringData() {
		final List<ModelType> modelTypes = modelTypeDataJPARepository.findByNameIsNull();

		assertEquals(1, modelTypes.size());
	}
}
