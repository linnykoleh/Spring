package com.ps.pet;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "P_PET")
public class Pet {

	@Id
	private Long id;
	@NotNull
	private Long ownerId;
	private String name;
	private Integer age;
	/**
	 * The pet has a RFID microchip implant.
	 */
	@NotEmpty
	@Column
	@Size(min = 10, max = 100)
	private String rfid;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "PET_TYPE")
	private PetType petType;

	public Pet() {
	}

	public Pet(Long ownerId, Long id, String name, PetType type, Integer age, String rfid) {
		this.ownerId = ownerId;
		this.id = id;
		this.name = name;
		this.age = age;
		this.rfid = rfid;
		this.petType = type;
	}

}
