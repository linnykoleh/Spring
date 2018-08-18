package com.ps.bk.hotel.room.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.bk.hotel.room.model.Room;
import com.ps.bk.hotel.room.service.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {

	private RoomService service;
	
	public RoomController(RoomService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Iterable<Room>> getAllRooms(){
		return ResponseEntity.ok(service.getAllRooms());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Room> findRoomById(long id){
		return ResponseEntity.ok(service.findRoom(id));
	}
	
	@PostMapping
	public ResponseEntity<?> addRoom(Room room){
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateRoom(Room room){
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRoom(long id){
		return ResponseEntity.ok().build();
	}
}
