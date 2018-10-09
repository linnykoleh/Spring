package com.learning.linnyk.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Shipwreck {

	private Long id;
	private String name;
	private String description;
	private String condition;
	private Integer depth;
	private Double latitude;
	private Double longitude;
	private Integer yearDiscovered;

}
