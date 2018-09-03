package com.ps.test;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ps.config.PetConfigClass;
import com.ps.ents.Pet;
import com.ps.services.PetService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PetConfigClass.class})
@ActiveProfiles("dev")
public class PetServiceTest {

    public static final Long PET_ID = 1L;

    @Autowired
    PetService simplePetService;

    //positive test, we know that a Pet with ID=1 exists
    @Test
    public void findByIdPositive() {
        Pet pet = simplePetService.findById(PET_ID);
        assertNull(pet);
    }

}
