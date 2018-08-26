package com.ps.bk.hotel.room.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ps.bk.hotel.room.exception.RoomServiceClientException;
import com.ps.bk.hotel.room.model.Room;
import com.ps.bk.hotel.room.repo.RoomRepo;
import com.ps.bk.hotel.room.service.RoomService;

public class TestRoomServiceImpl {

	@Test
	@DisplayName("AssertJ and JUnit 5 are great! 🇺🇸")
	public void testFindByValidRoomNumber() {
		
		//Given
		final RoomRepo roomRepo = mock(RoomRepo.class);
		final Room room = new Room();
		room.setRoomNumber("1023");
		room.setFloor("10");
		room.setRoomType("Double");
		room.setWeekdayPrice(150.99);
		room.setWeekendPrice(180.99);
		when(roomRepo.findByRoomNumber("1023")).thenReturn(room);

		final RoomService service = new RoomServiceImpl(roomRepo, null);
		
		//When
		final Room returnedRoom = service.findByRoomNumber("1023");
		
		//Then
		assertAll(() -> assertThat(returnedRoom.getRoomNumber()).isEqualTo("1023"),
				() -> assertThat(returnedRoom.getFloor()).isEqualTo("10"),
				() -> assertThat(returnedRoom.getRoomType()).isEqualTo("Double"),
				() -> assertThat(returnedRoom.getWeekdayPrice()).isEqualTo(150.99),
				() -> assertThat(returnedRoom.getWeekendPrice()).isEqualTo(180.99));
	}
	
	@Test
	@DisplayName("Testing room lookup by floor")
	public void testRoomsByFloor() {
		// Given
		final RoomRepo roomRepo = mock(RoomRepo.class);

		final Room room1 = new Room();
		room1.setRoomNumber("1023");
		room1.setFloor("10");
		room1.setRoomType("Double");
		room1.setWeekdayPrice(150.99);
		room1.setWeekendPrice(180.99);

		final Room room2 = new Room();
		room2.setRoomNumber("1024");
		room2.setFloor("10");
		room2.setRoomType("Single");
		room2.setWeekdayPrice(100.99);
		room2.setWeekendPrice(120.99);

		final Room room3 = new Room();
		room3.setRoomNumber("1025");
		room3.setFloor("10");
		room3.setRoomType("Suite");
		room3.setWeekdayPrice(200.99);
		room3.setWeekendPrice(250.99);

		final List<Room> rooms = Arrays.asList(room1, room2, room3);
		when(roomRepo.findRoomsByFloor("10")).thenReturn(rooms);

		final RoomService service = new RoomServiceImpl(roomRepo, null);
		
		//When
		final List<Room> returnedRooms = service.findRoomsByFloor("10");

		//Then
		assertThat(returnedRooms)
                .extracting("roomType", "roomNumber")
                .containsExactly(
                        tuple("Double", "1023"),
                        tuple("Single", "1024"),
                        tuple("Suite","1025")
                );
	}
	
	
	@Test
	public void testThrowExceptionWhenRoomNotFound() {
		final RoomRepo roomRepo = mock(RoomRepo.class);

		when(roomRepo.findByRoomNumber("456")).thenReturn(null);
		final RoomService service = new RoomServiceImpl(roomRepo, null);
		try{
			service.findByRoomNumber("456");
			fail("Exception should had been thrown!");
		} catch(Exception e) {
			assertThat(e).isInstanceOf(RoomServiceClientException.class).extracting("message").containsExactly("Room number 456 does not exist!");
		}
	}
	
	@Test
	public void testThrowExceptionForInvalidRoomNumberFormat() {
		final RoomRepo roomRepo = mock(RoomRepo.class);

		verify(roomRepo, times(0)).findByRoomNumber(any());
		final RoomService service = new RoomServiceImpl(roomRepo, null);
		try{
			service.findByRoomNumber("INVALID");
			fail("Exception should had been thrown!");
		} catch(Exception e) {
			assertThat(e).isInstanceOf(RoomServiceClientException.class)
                    .extracting("message")
                    .containsExactly("Room number INVALID is not a valid format!");
		}
	}
}
