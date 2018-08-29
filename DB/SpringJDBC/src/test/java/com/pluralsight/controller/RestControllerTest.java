package com.pluralsight.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

import org.junit.Test;

public class RestControllerTest {

	@Test(timeout = 10000)
	public void testCreateRide() {
		final RestTemplate restTemplate = new RestTemplate();

		Ride ride = new Ride();
		ride.setName("Bobsled Trail Ride");
		ride.setDuration(35);

		ride = restTemplate.postForObject("http://localhost:8090/jdbc/ride", ride, Ride.class);
		System.out.println(ride);
	}

	@Test(timeout = 10000)
	public void testGetRides() {
		final RestTemplate restTemplate = new RestTemplate();
		final ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:8090/jdbc/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println(ride);
		}
	}
}
