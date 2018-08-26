package com.ps.bk.hotel.booking.service;

import com.ps.bk.hotel.booking.model.Booking;

public interface BookingValidationService {
	boolean doesBookingExist(long id);
	boolean isBookingValid(Booking booking);
}
