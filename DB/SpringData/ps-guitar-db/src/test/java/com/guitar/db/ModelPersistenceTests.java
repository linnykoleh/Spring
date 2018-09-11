package com.guitar.db;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.guitar.db.repository.spring_data.ModelDataJPARepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.guitar.db.model.Model;
import com.guitar.db.repository.ModelRepository;

@ContextConfiguration(locations={"classpath:com/guitar/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ModelPersistenceTests {

	@Autowired
	private ModelRepository modelRepository;

	@Autowired
	private ModelDataJPARepository modelDataJPARepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() {
		Model m = new Model();
		m.setFrets(10);
		m.setName("Test Model");
		m.setPrice(BigDecimal.valueOf(55L));
		m.setWoodType("Maple");
		m.setYearFirstMade(new Date());

		m = modelRepository.create(m);
		
		// clear the persistence context so we don't return the previously cached location object
		// this is a test only thing and normally doesn't need to be done in prod code
		entityManager.clear();

		Model otherModel = modelRepository.find(m.getId());
		assertEquals("Test Model", otherModel.getName());
		assertEquals(10, otherModel.getFrets());
		
		//delete BC location now
		modelRepository.delete(otherModel);
	}

	@Test
	public void testGetModelsInPriceRange() {
		List<Model> mods = modelRepository.getModelsInPriceRange(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L));
		assertEquals(4, mods.size());
	}

	@Test
	public void testCustomMethod() {
		modelDataJPARepository.aCustomMethod();
	}

	@Test
	public void testGetModelsByPriceRangeAndWoodType() {
		final List<Model> mods = modelRepository
				.getModelsByPriceRangeAndWoodType(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L), "Maple");

		assertEquals(3, mods.size());
	}

	@Test
	public void testQueryByPriceRangeAndWoodType_SpringData() {
		final List<Model> mods = modelDataJPARepository
				.queryByPriceRangeAndWoodType(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L), "%Maple%");

		assertEquals(3, mods.size());
	}

	@Test
	public void testQueryByPriceRangeAndWoodTypePaging_SpringData() {
		final Sort sort = new Sort(Sort.Direction.DESC, "name");
		final Pageable pageable = new PageRequest(0, 2, sort);

		Page<Model> page = modelDataJPARepository
				.queryByPriceRangeAndWoodTypePaging(
						BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L), "%Maple%", pageable);
		/* select name from Model m where m.price>=? and m.price<=? and (m.woodType like ?) order by m.name desc limit ? */

		Iterator<Model> iterator = page.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		// SG
		// Les Paul

		page = modelDataJPARepository
				.queryByPriceRangeAndWoodTypePaging(
						BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L), "%Maple%", page.nextPageable());

		/* select name from Model m where m.price>=? and m.price<=? and (m.woodType like ?) order by m.name desc limit ? offset ?*/
		iterator = page.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		// American Stratocaster
	}

	@Test
	public void testGetModelsByType() {
        final List<Model> mods = modelRepository.getModelsByType("Electric");

		assertEquals(4, mods.size());
	}

    @Test
    public void testFindAllModelsByType_SpringData() {
        final List<Model> mods = modelDataJPARepository.findAllModelsByType("Electric");

        assertEquals(4, mods.size());
    }

	@Test
	public void testFindAllModelsByTypeUsingQuery_SpringData() {
		final List<Model> mods = modelDataJPARepository.findAllModelsByTypeUsingQuery("Electric");

		assertEquals(4, mods.size());
	}

	@Test
	public void testGetModelsGreaterThanEqualAndLessThanEqual_SpringData() {
		final List<Model> mods = modelDataJPARepository
				.findByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal.valueOf(100), BigDecimal.valueOf(1000));

		assertEquals(5, mods.size());
	}

	@Test
	public void testFindByModelTypeNameIn_SpringData() {
		final List<Model> mods = modelDataJPARepository
				.findByModelTypeNameIn(Arrays.asList("Electric", "Acoustic"));

		assertEquals(4, mods.size());
	}
}
