package com.ps.bk.hotel.booking.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ps.bk.hotel.booking.model.Booking;
import com.ps.bk.hotel.booking.repo.BookingRepo;
import com.ps.bk.hotel.booking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	private BookingRepo repo;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	public BookingServiceImpl(BookingRepo repo) {
		this.repo = repo;
		dateFormat.setLenient(false);
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

	@Override
	public List<String> validateBookingDates(String checkInDate, String checkOutDate) {
		List<String> errors = new ArrayList<>();
		Date checkIn;
		Date checkOut;

		errors.addAll(validateDate(checkInDate, "check-in"));
		errors.addAll(validateDate(checkOutDate, "check-out"));
		if (errors.isEmpty()) {
			try {
				checkIn = dateFormat.parse(checkInDate);
				checkOut = dateFormat.parse(checkOutDate);
				if (checkOut.before(checkIn) || checkOut.equals(checkIn)) {
					errors.add("Check-out date must occur after check-in date");
				}
			} catch (ParseException e) {
			}
		}

		return errors;
	}

	private List<String> validateDate(String formattedDate, String dateType) {
		List<String> errors = new ArrayList<>();
		if (formattedDate == null) {
			errors.add("Must provide a " + dateType + " date.");
		} else {
			Date date;
			try {
				date = dateFormat.parse(formattedDate);
				if(date.getTime() < System.currentTimeMillis()) {
					errors.add(dateType + ": " + formattedDate + " is in the past.");
				}
			} catch (ParseException e) {
				errors.add(dateType + " date of: " + formattedDate
						+ " is not a valid date or does not match date format of: MM/DD/YYYY");
			}
		}
		return errors;
	}

}
