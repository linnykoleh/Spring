package com.ps.services;

import java.util.Set;

import com.ps.base.PetType;
import com.ps.ents.Pet;
import com.ps.ents.User;

public interface PetService extends AbstractService<Pet> {

    Pet createPet(User user, String name, int age, PetType petType, String rfid);

    Set<Pet> findAllByOwner(User user);

    Pet findByOwner(User user, String name);
}
