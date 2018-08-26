package com.ps.bk.hotel.room.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ps.bk.hotel.room.model.Room;

public interface RoomRepo extends CrudRepository<Room, Long> {
	Room findByRoomNumber(String roomNumber);
	List<Room> findRoomsByFloor(String floorNumber);
}
