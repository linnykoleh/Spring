package com.learning.linnyk.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.learning.linnyk.domain.City;

@Repository
public class CitiesRepository {

	private static final Map<Long, City> dataBase = new HashMap<>();

	static {
		dataBase.put(1L, new City(1L, "London", 1220000, 500));
		dataBase.put(2L, new City(2L, "Paris", 3920000, 600));
		dataBase.put(3L, new City(3L, "Kiev", 1920000, 1500));
	}

	public City getCityById(long id) {
		System.out.printf("Read city by id: %s from database", id);
		System.out.println();
		return dataBase.get(id);
	}

}
