package com.ps.bk.hotel.room.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.ps.bk.hotel.room.exception.RoomServiceClientException;
import com.ps.bk.hotel.room.model.Room;
import com.ps.bk.hotel.room.repo.RoomRepo;
import com.ps.bk.hotel.room.service.RoomService;

public class TestRoomServiceImpl {
	
	@Test
	public void testFindByValidRoomNumber() {
		//
		// Given 
		//
		RoomRepo roomRepo = mock(RoomRepo.class);
		Room room = new Room();
		room.setRoomNumber("123");
		
		//When
		when(roomRepo.findByRoomNumber("123")).thenReturn(room);
		RoomService service = new RoomServiceImpl(roomRepo, null);
		
		//Then
		assertThat(service.findByRoomNumber("123")).isEqualTo(room);
	}
	
	@Test
	public void testThrowExceptionWhenRoomNotFound() {
		RoomRepo roomRepo = mock(RoomRepo.class);

		when(roomRepo.findByRoomNumber("456")).thenReturn(null);
		RoomService service = new RoomServiceImpl(roomRepo, null);
		try{
			service.findByRoomNumber("456");
			fail("Exception should had been thrown!");
		} catch(Exception e) {
			assertThat(e).isInstanceOf(RoomServiceClientException.class).extracting("message").containsExactly("Room number 456 does not exist!");
		}
	}
	
	@Test
	public void testThrowExceptionForInvalidRoomNumberFormat() {
		RoomRepo roomRepo = mock(RoomRepo.class);

		verify(roomRepo, times(0)).findByRoomNumber(any());
		RoomService service = new RoomServiceImpl(roomRepo, null);
		try{
			service.findByRoomNumber("INVALID");
			fail("Exception should had been thrown!");
		} catch(Exception e) {
			assertThat(e).isInstanceOf(RoomServiceClientException.class).extracting("message").containsExactly("Room number INVALID is not a vliad format!");
		}
	}
}
