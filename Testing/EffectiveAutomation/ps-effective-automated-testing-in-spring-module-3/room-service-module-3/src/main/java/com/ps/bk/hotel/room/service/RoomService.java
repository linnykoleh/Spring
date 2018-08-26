package com.ps.bk.hotel.room.service;

import com.ps.bk.hotel.room.model.Room;

public interface RoomService {
	Iterable<Room> getAllRooms();
	Room findRoom(long roomId);
	void updateRoom(Room room);
	void addRoom(Room room);
	Room findByRoomNumber(String string);
}
