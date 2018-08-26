package com.ps.bk.hotel.room.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.ps.bk.hotel.room.model.Room;
import com.ps.bk.hotel.room.repo.RoomRepo;
import com.ps.bk.hotel.room.service.RoomService;

public class TestRoomServiceImpl {

	@Test
	public void lookupExistingRoom(){
		RoomRepo mockRepo = mock(RoomRepo.class);
		when(mockRepo.findByRoomNumber(anyString())).thenReturn(new Room());
		RoomService service = new RoomServiceImpl(mockRepo);
		
		Room room = service.findByRoomNumber("100");
		
		assertNotNull(room);
	}
	
	@Test
	public void throwExceptionForNonExistingRoom(){
		RoomRepo mockRepo = mock(RoomRepo.class);
		when(mockRepo.findByRoomNumber(anyString())).thenReturn(null);
		RoomService service = new RoomServiceImpl(mockRepo);
		try{
			service.findByRoomNumber("100");
			fail("Exception should had been thrown");
		} catch(Exception e){
			assertEquals("Room number: 100, does not exist.", e.getMessage());
		}
	}
	
	@Test
	public void throwExceptionInvalidRoomNumberFormat(){
		RoomRepo mockRepo = mock(RoomRepo.class);
		RoomService service = new RoomServiceImpl(mockRepo);
		try{
			service.findByRoomNumber("BAD ROOM NUMBER!");
			fail("Exception should had been thrown");
		} catch(Exception e){
			assertEquals("Room number: BAD ROOM NUMBER!, is an invalid room number format.", e.getMessage());
		}
	}
	
	@Test
	public void throwExceptionInvalidRoomNumberNull(){
		RoomRepo mockRepo = mock(RoomRepo.class);
		RoomService service = new RoomServiceImpl(mockRepo);
		try{
			service.findByRoomNumber(null);
			fail("Exception should had been thrown");
		} catch(Exception e){
			assertEquals("Room number: null, is an invalid room number format.", e.getMessage());
		}
	}
}
