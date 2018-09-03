package com.ps.repos;

import com.ps.ents.Pet;
import com.ps.ents.User;

import java.util.Set;

public interface PetRepo {

    Set<Pet> findByOwner(User owner);
}
