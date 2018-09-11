package com.guitar.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.guitar.db.model.Location;
import com.guitar.db.repository.LocationRepository;
import com.guitar.db.repository.spring_data.LocationDataJPARepository;

@ContextConfiguration(locations={"classpath:com/guitar/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class LocationPersistenceTests {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private LocationDataJPARepository locationDataJPARepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() {
		Location location = new Location();
		location.setCountry("Canada");
		location.setState("British Columbia");
		location = locationRepository.create(location);
		
		// clear the persistence context so we don't return the previously cached location object
		// this is a test only thing and normally doesn't need to be done in prod code
		entityManager.clear();

		Location otherLocation = locationRepository.find(location.getId());
		assertEquals("Canada", otherLocation.getCountry());
		assertEquals("British Columbia", otherLocation.getState());
		
		//delete BC location now
		locationRepository.delete(otherLocation);
	}

	@Test
	public void testFindAll() {
		final List<Location> locations = locationDataJPARepository.findAll();

		assertNotNull(locations);
		assertEquals(50, locations.size());
	}

	@Test
	public void testFindWithLike() {
		final List<Location> locs = locationRepository.getLocationByStateName("New");

		assertEquals(4, locs.size());
	}

	@Test
	public void testFindWithLike_SpringData() {
		final List<Location> locations = locationDataJPARepository.findByStateLike("New%");

		assertEquals(4, locations.size());
	}

	@Test
	public void testFindWithNotLike_SpringData() {
		final List<Location> locations = locationDataJPARepository.findByStateNotLike("New%");

		assertEquals(46, locations.size());
	}

	@Test
	public void testFindWithAnd_SpringData() {
		final List<Location> locations = locationDataJPARepository.findByStateAndCountry("Utah", "United States");

		assertEquals(1, locations.size());
	}

	@Test
	public void testFindWithOr_SpringData() {
		final List<Location> locations = locationDataJPARepository.findByStateOrCountry("Utah", "United");

		assertEquals(1, locations.size());
	}

	@Test
	public void testFindWithAndNot_SpringData() {
		final List<Location> locations = locationDataJPARepository.findByStateNotAndCountry("Utah", "United States");

		assertEquals(49, locations.size());
	}

	@Test
	public void testFindWithStartingWith_SpringData() {
		final List<Location> locations = locationDataJPARepository.findByStateStartingWith("Uta");

		assertEquals(1, locations.size());
	}

	@Test
	public void testFindByStateIgnoreCaseStartingWith_SpringData() {
		final List<Location> locations = locationDataJPARepository.findByStateIgnoreCaseStartingWith("new");

		assertEquals(4, locations.size());
	}

	@Test
	public void testFindByCountryLikeOrderByStateAsc_SpringData() {
		final List<Location> locations = locationDataJPARepository.findByCountryLikeOrderByStateAsc("United%");

		assertEquals(50, locations.size());
	}

	@Test
	public void testFindTop5ByStateLike_SpringData() {
		final List<Location> locations = locationDataJPARepository.findTop5ByStateLike("Ne%");

		assertEquals(5, locations.size());
	}

	@Test
	public void testFindDistinctByStateIsNotLike_SpringData() {
		final Location location = locationDataJPARepository.findDistinctByState("New Mexico");

		assertNotNull(location);
	}

	@Test
	@Transactional  //note this is needed because we will get a lazy load exception unless we are in a tx
	public void testFindWithChildren() {
		final Location arizona = locationRepository.find(3L);

		assertEquals("United States", arizona.getCountry());
		assertEquals("Arizona", arizona.getState());
		
		assertEquals(1, arizona.getManufacturers().size());
		
		assertEquals("Fender Musical Instruments Corporation", arizona.getManufacturers().get(0).getName());
	}
}
