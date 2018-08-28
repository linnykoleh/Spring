package com.ps.services;

import com.ps.base.PetType;
import com.ps.ents.Pet;
import com.ps.ents.User;

public interface PetService {

    Pet createPet(User user, String name, int age, PetType petType, String rfid);

}
