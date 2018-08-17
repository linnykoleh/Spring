package com.ps.bk.hotel.room.service.impl;

import org.h2.util.StringUtils;

import com.ps.bk.hotel.room.exception.RoomServiceClientException;
import com.ps.bk.hotel.room.model.Room;
import com.ps.bk.hotel.room.repo.RoomRepo;
import com.ps.bk.hotel.room.service.RoomService;

public class RoomServiceImpl implements RoomService {
	private RoomRepo repo;

	public RoomServiceImpl(RoomRepo repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<Room> getAllRooms() {
		return repo.findAll();
	}

	@Override
	public Room findRoom(long roomId) {
		return repo.findOne(roomId);
	}

	@Override
	public void updateRoom(Room room) {
		repo.save(room);
	}

	@Override
	public void addRoom(Room room) {
		repo.save(room);
	}

	@Override
	public Room findByRoomNumber(String roomNumber) {
		if (!StringUtils.isNullOrEmpty(roomNumber) && StringUtils.isNumber(roomNumber)) {
			Room room = repo.findByRoomNumber(roomNumber);
			if (room == null) {
				throw new RoomServiceClientException("Room number: " + roomNumber + ", does not exist.");
			}
			return room;
		}
		else {
			throw new RoomServiceClientException("Room number: " + roomNumber + ", is an invalid room number format.");
		}
	}

}
