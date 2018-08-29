package com.pluralsight.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

import org.junit.Test;

public class RestControllerTest {

	@Test(timeout = 3000)
	public void testCreateRide() {
		final RestTemplate restTemplate = new RestTemplate();
		final Ride ride = new Ride();
		ride.setName("Bobsled Trail Ride");
		ride.setDuration(35);

		restTemplate.put("http://localhost:8090/jdbc/ride", ride);
	}

	@Test(timeout=3000)
	public void testGetRides() {
		final RestTemplate restTemplate = new RestTemplate();
		final ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:8090/jdbc/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}
	}
}
