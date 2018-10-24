package com.ps.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.exceptions.PetNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/pets")
public class PetController {

	private final PetRepo petRepository;

	@Autowired
	public PetController(PetRepo petRepository) {
		this.petRepository = petRepository;
	}

	@RequestMapping("/")
	public List<Pet> all() {
		List<Pet> pets = petRepository.findAll();
		log.info("Pest extracted");
		if (pets.isEmpty()) {
			throw new PetNotFoundException("all");
		} else {
			return pets;
		}
	}

	@RequestMapping("/{rfid}")
	public Pet byRfid(@PathVariable("rfid") String rfid) {
		Pet pet = petRepository.findByRfId(rfid);
		if (pet == null) {
			throw new PetNotFoundException(rfid);
		} else {
			return pet;
		}
	}

	@RequestMapping("/type/{type}")
	public List<Pet> byPetType(@PathVariable("type") String type) {
		return petRepository.findAllByType(PetType.valueOf(type.toUpperCase()));
	}

	@RequestMapping("/owner/{id}")
	public List<Pet> byOwner(@PathVariable("id") Long ownerId) {
		final List<Pet> pets = petRepository.findAllByOwner(ownerId);
		log.info("Pets found for owner" + ownerId + ", " + pets);

		if (pets.isEmpty()) {
			throw new PetNotFoundException(ownerId.toString());
		} else {
			return pets;
		}
	}
}
