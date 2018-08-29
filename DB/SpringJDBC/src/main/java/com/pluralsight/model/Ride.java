package com.pluralsight.model;

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
	
}
