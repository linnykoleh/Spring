package com.learning.linnyk;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.learning.linnyk.config.AppConfig;
import com.learning.linnyk.domain.City;
import com.learning.linnyk.services.CitiesService;

public class App {

	public static void main(String[] args) {
		final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		final CitiesService citiesService = applicationContext.getBean(CitiesService.class);

		City cityById1 = citiesService.getCityByIdCacheable(1L);
		System.out.println(cityById1);

		cityById1 = citiesService.getCityByIdCacheable(1L);
		System.out.println(cityById1);

		// Read city by id: 1 from database
		// City(id=1, name=London, population=1220000, year=500)
		// City(id=1, name=London, population=1220000, year=500)

		City cityById2 = citiesService.getCityByIdCacheable(2L);
		System.out.println(cityById2);

		cityById2 = citiesService.getCityByIdCacheable(2L);
		System.out.println(cityById2);

		// Read city by id: 2 from database
		// City(id=2, name=Paris, population=3920000, year=600)
		// City(id=2, name=Paris, population=3920000, year=600)

		City cityById3 = citiesService.getCityByIdCachePut(3L);
		System.out.println(cityById3);

		cityById3 = citiesService.getCityByIdCacheable(3L);
		System.out.println(cityById3);

		// Read city by id: 3 from database
		// City(id=3, name=Kiev, population=1920000, year=1500)
		// City(id=3, name=Kiev, population=1920000, year=1500)


	}
}
