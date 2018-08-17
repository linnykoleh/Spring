package com.ps.bk.hotel.room.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Java class representation of a record in the Rooms table.
 */
@Entity
@Table(name="rooms")
public class Room {
	@Id
	@GeneratedValue
	private long id;
	@Column(name="room_number")
	private String roomNumber;
	@Column(name="weekday_price")
	private double weekdayPrice;
	@Column(name="weekend_price")
	private double weekendPrice;
	@Column(name="room_type")
	private String roomType;
	@Column(name="floor")
	private String floor;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public double getWeekdayPrice() {
		return weekdayPrice;
	}
	public void setWeekdayPrice(double weekdayPrice) {
		this.weekdayPrice = weekdayPrice;
	}
	public double getWeekendPrice() {
		return weekendPrice;
	}
	public void setWeekendPrice(double weekendPrice) {
		this.weekendPrice = weekendPrice;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
}