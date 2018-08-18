package com.ps.bk.hotel.room.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Java class representation of a record in the Rooms table. 
 * @author williamkorando
 *
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((floor == null) ? 0 : floor.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((roomNumber == null) ? 0 : roomNumber.hashCode());
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(weekdayPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(weekendPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (floor == null) {
			if (other.floor != null)
				return false;
		} else if (!floor.equals(other.floor))
			return false;
		if (id != other.id)
			return false;
		if (roomNumber == null) {
			if (other.roomNumber != null)
				return false;
		} else if (!roomNumber.equals(other.roomNumber))
			return false;
		if (roomType == null) {
			if (other.roomType != null)
				return false;
		} else if (!roomType.equals(other.roomType))
			return false;
		if (Double.doubleToLongBits(weekdayPrice) != Double.doubleToLongBits(other.weekdayPrice))
			return false;
		if (Double.doubleToLongBits(weekendPrice) != Double.doubleToLongBits(other.weekendPrice))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Room [id=" + id + ", roomNumber=" + roomNumber + ", weekdayPrice=" + weekdayPrice + ", weekendPrice="
				+ weekendPrice + ", roomType=" + roomType + ", floor=" + floor + "]";
	}
	
	
}