package com.ps.bk.hotel.booking.service.impl;

import org.springframework.stereotype.Service;

import com.ps.bk.hotel.booking.model.Booking;
import com.ps.bk.hotel.booking.repo.BookingRepo;
import com.ps.bk.hotel.booking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	private BookingRepo repo;
	
	public BookingServiceImpl(BookingRepo repo) {
		this.repo = repo;
	}

	@Override
	public Booking getBookingById(long id) {
		return repo.findOne(id);
	}

	@Override
	public Iterable<Booking> getBookings() {
		return repo.findAll();
	}

	@Override
	public Long addBooking(Booking booking) {
		return repo.save(booking).getId();
	}

	@Override
	public void updateBooking(Booking booking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBooking(long id) {
		repo.delete(id);
	}

}
