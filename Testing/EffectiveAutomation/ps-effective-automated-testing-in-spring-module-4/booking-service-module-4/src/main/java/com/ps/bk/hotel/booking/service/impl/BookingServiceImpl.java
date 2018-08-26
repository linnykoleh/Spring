package com.ps.bk.hotel.booking.service.impl;

import com.ps.bk.hotel.booking.model.Booking;
import com.ps.bk.hotel.booking.repo.BookingRepo;
import com.ps.bk.hotel.booking.service.BookingService;

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
	public void addBooking(Booking booking) {
		repo.save(booking);
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
