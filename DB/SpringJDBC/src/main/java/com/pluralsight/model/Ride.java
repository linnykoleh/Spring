package com.pluralsight.model;

import java.util.Date;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ride {

	private int id;
	private String name;
	private int duration;
	private Date rideDate;

}
