package com.ps.bk.hotel.booking.repo;

import org.springframework.data.repository.CrudRepository;

import com.ps.bk.hotel.booking.model.Booking;

public interface BookingRepo extends CrudRepository<Booking, Long> {

}
