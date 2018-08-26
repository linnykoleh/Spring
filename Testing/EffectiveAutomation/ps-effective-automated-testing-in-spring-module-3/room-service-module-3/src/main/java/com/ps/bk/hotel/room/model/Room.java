package com.ps.bk.hotel.room.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}