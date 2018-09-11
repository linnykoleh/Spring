package com.guitar.db;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import com.guitar.db.repository.spring_data.ManufacturerDataJPARepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guitar.db.model.Manufacturer;
import com.guitar.db.repository.ManufacturerRepository;

@ContextConfiguration(locations = {"classpath:com/guitar/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ManufacturerPersistenceTests {

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Autowired
	private ManufacturerDataJPARepository manufacturerDataJPARepository;

	@Test
	public void testGetManufacturersFoundedBeforeDate() {
		List<Manufacturer> mans = manufacturerRepository.getManufacturersFoundedBeforeDate(new Date());
		assertEquals(2, mans.size());
	}

	@Test
	public void testGetManufactureByName() {
		Manufacturer m = manufacturerRepository.getManufacturerByName("Fender");
		assertEquals("Fender Musical Instruments Corporation", m.getName());
	}

	@Test
	public void testGetManufacturersThatSellModelsOfType() {
		final List<Manufacturer> mans = manufacturerRepository.getManufacturersThatSellModelsOfType("Semi-Hollow Body Electric");

		assertEquals(1, mans.size());
	}

	@Test
	public void testGetAllThatSellAcoustics_SpringData() {
		final List<Manufacturer> mans = manufacturerDataJPARepository.getAllThatSellAcoustics("Semi-Hollow Body Electric");

		assertEquals(1, mans.size());
	}

	@Test
	public void testQueryAllThatSellAcoustics_SpringData() {
		final List<Manufacturer> mans = manufacturerDataJPARepository.queryAllThatSellAcoustics("Semi-Hollow Body Electric");

		assertEquals(1, mans.size());
	}

	@Test
	public void testGetManufacturersByFoundedDateBefore_SpringData() {
		final List<Manufacturer> mans = manufacturerDataJPARepository.findByFoundedDateBefore(new Date());

		assertEquals(2, mans.size());
	}


	@Test
	public void testFindByActiveFalse_SpringData() {
		final List<Manufacturer> mans = manufacturerDataJPARepository.findByActiveFalse();

		assertEquals(1, mans.size());
	}

	@Test
	public void testFindByActiveTrue_SpringData() {
		final List<Manufacturer> mans = manufacturerDataJPARepository.findByActiveTrue();

		assertEquals(1, mans.size());
	}
}
