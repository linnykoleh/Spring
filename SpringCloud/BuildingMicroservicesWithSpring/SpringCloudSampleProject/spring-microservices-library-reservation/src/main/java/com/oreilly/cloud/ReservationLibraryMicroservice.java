package com.oreilly.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class ReservationLibraryMicroservice {

	@Value("${server.port}")
	private String port;

	private Map<Integer, Reservation> reservations = new HashMap<>();

	@CrossOrigin
	@RequestMapping("/reservation/user/{username}/book/{bookId}")
	public String reserve(@PathVariable("username") String username, @PathVariable("bookId") int bookId) {
		Reservation reservation = new Reservation();
		reservation.setBookId(bookId);
		reservation.setUsername(username);
		reservation.setDate(new Date());
		reservation.setReservationId(new Random().nextInt(100));
		reservations.put(reservation.getReservationId(), reservation);
		System.out.println(reservations.size());
		return "Title has been reserved using server on port: " + port + ".";
	}

	@CrossOrigin
	@RequestMapping("/reservation/user/{username}")
	public List<Reservation> reservationsByUser(@PathVariable("username") String username) {
		List<Reservation> tmpReservations = new ArrayList<>();
		for (Reservation reservation : this.reservations.values()) {
			if (reservation.getUsername().equals(username)) {
				tmpReservations.add(reservation);
			}
		}
		return tmpReservations;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReservationLibraryMicroservice.class, args);
	}
}
