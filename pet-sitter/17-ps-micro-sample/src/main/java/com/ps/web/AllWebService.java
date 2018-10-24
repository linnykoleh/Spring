package com.ps.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ps.exceptions.UserNotFoundException;
import com.ps.pet.Pet;
import com.ps.user.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AllWebService {

	@LoadBalanced
	@Autowired
	private RestTemplate restTemplate;

	private String petsServiceUrl;
	private String usersServiceUrl;

	public AllWebService(String usersServiceUrl, String petsServiceUrl) {
		this.usersServiceUrl = usersServiceUrl;
		this.petsServiceUrl = petsServiceUrl;
	}

	public UserSkeleton findUserById(Long id) {
		log.info("findUserById({}) called", id);
		User user;
		try {
			user = restTemplate.getForObject(usersServiceUrl + "/users/id/{id}", User.class, id);
		}
		catch (HttpClientErrorException e) {
			throw new UserNotFoundException(id);
		}
		return new UserSkeleton(user.getId(), user.getUsername());
	}

	public List<PetSkeleton> findByOwnerId(Long ownerId) {
		log.info("findByOwnerId({}) called", ownerId);
		Pet[] pets = null;
		try {
			pets = restTemplate.getForObject(petsServiceUrl + "/pets/owner/{id}",
					com.ps.pet.Pet[].class, ownerId);
		}
		catch (HttpClientErrorException e) {
			// no pets
		}

		if (pets == null || pets.length == 0) {
			return new ArrayList<>();
		}
		List<PetSkeleton> petsList = new ArrayList<>();
		for (Pet pet : pets) {
			petsList.add(new PetSkeleton(pet.getName(), pet.getAge(), pet.getPetType().toString()));
		}
		return petsList;
	}

	public List<PetSkeleton> findByType(String type) {
		log.info("findByType({}) called", type);

		final Pet[] pets = restTemplate.getForObject(petsServiceUrl + "/pets/type/{type}", com.ps.pet.Pet[].class, type);
		final List<PetSkeleton> petsList = new ArrayList<>();
		for (Pet pet : pets) {
			petsList.add(new PetSkeleton(pet.getName(), pet.getAge(), pet.getPetType().toString()));
		}
		return petsList;
	}

}
