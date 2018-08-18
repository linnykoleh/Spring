package com.ps.test.room.room.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.ps.bk.hotel.room.model.Booking;
import com.ps.bk.hotel.room.service.RoomService;
import com.ps.bk.hotel.room.RoomServiceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= RoomServiceApplication.class)
@AutoConfigureStubRunner(ids = "com.ps.bk.hotel:booking-service:+:8081", workOffline = true)
public class ContractValidatorRoomService {

	@Autowired
	private RoomService roomService;

	@Test
	public void successfullyBookRoom() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		Booking booking = new Booking();
		booking.setCustomerId("100000000A");
		booking.setRoomNumber("1001");
		booking.setCheckIn(dateFormat.parse("10-31-2017"));
		booking.setCheckOut(dateFormat.parse("11-05-2017"));
		String url = roomService.bookRoom(booking);
		
		assertEquals("bookings/1", url);
	}

}