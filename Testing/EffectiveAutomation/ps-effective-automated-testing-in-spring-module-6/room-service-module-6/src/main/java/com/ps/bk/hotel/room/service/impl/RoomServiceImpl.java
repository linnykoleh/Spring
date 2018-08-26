package com.ps.bk.hotel.room.service.impl;

import java.util.List;

import org.h2.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ps.bk.hotel.room.exception.RoomServiceClientException;
import com.ps.bk.hotel.room.model.Booking;
import com.ps.bk.hotel.room.model.Room;
import com.ps.bk.hotel.room.repo.RoomRepo;
import com.ps.bk.hotel.room.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {
	private RoomRepo repo;
	private RestTemplate restTemplate; 

	public RoomServiceImpl(RoomRepo repo, RestTemplate restTemplate) {
		this.repo = repo;
		this.restTemplate = restTemplate;
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

	@Override
	public String bookRoom(Booking booking) {
		ResponseEntity<?> entity = restTemplate.postForEntity("http://localhost:8081/bookings/", booking, null);
		return entity.getHeaders().getLocation().toString();
	}

	@Override
	public List<Room> findRoomsByFloor(String floorNumber) {
		return repo.findRoomsByFloor(floorNumber);
	}

}
