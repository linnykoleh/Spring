package com.learning.linnyk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.learning.linnyk.domain.City;
import com.learning.linnyk.repositories.CitiesRepository;

@Service
public class CitiesService {

	private CitiesRepository citiesRepository;

	@Autowired
	public CitiesService(CitiesRepository citiesRepository) {
		this.citiesRepository = citiesRepository;
	}

	@Cacheable(value = "cities")
	public City getCityByIdCacheable(long id) {
		return citiesRepository.getCityById(id);
	}

	@CachePut(value = "cities")
	public City getCityByIdCachePut(long id) {
		return citiesRepository.getCityById(id);
	}
}
