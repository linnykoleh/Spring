package com.ps.bk.hotel.booking.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.bk.hotel.booking.model.Booking;
import com.ps.bk.hotel.booking.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	private BookingService service;

	public BookingController(BookingService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<URI> reserveRoom(Booking booking) {
		return ResponseEntity.created(URI.create("bookings/" + service.addBooking(booking))).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Booking> lookupBooking(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.getBookingById(id));
	}
}
