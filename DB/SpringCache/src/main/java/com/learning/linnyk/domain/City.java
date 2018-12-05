package com.learning.linnyk.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class City {

	private long id;
	private String name;
	private int population;
	private int year;

}
