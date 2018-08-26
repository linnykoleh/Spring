package com.ps.bk.hotel.room.service;

import java.util.List;

import com.ps.bk.hotel.room.model.Booking;
import com.ps.bk.hotel.room.model.Room;

public interface RoomService {
	Iterable<Room> getAllRooms();
	Room findRoom(long roomId);
	void updateRoom(Room room);
	void addRoom(Room room);
	Room findByRoomNumber(String string);
	String bookRoom(Booking booking);
	List<Room> findRoomsByFloor(String floorNumber);
}
