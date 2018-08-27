package com.ps.repo.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.ps.base.PetType;
import com.ps.base.RequestStatus;
import com.ps.base.ResponseStatus;
import com.ps.base.ReviewGrade;
import com.ps.base.UserType;
import com.ps.ents.Pet;
import com.ps.ents.Request;
import com.ps.ents.Response;
import com.ps.ents.Review;
import com.ps.ents.User;
import com.ps.util.Pair;


public class SimpleOperationsServiceTest extends SimpleServiceTestBase {

    public static final Long OWNER_ID=1L;
    public static final Long SITTER_ID=2L;

    public static final Long REQUEST_ID=1L;

    @Before
    public void setup() throws  Exception {
       init();
        //create an owner
        User owner = userService.createUser("iuliana.cosmina@gmail.com", "test", UserType.OWNER);
        assertNotNull(owner);
        assertTrue(owner.getId() == OWNER_ID);

        //create an sitter
        User sitter = userService.createUser("vlad.bertrand@gmail.com", "test", UserType.SITTER);
        assertNotNull(sitter);
        assertTrue(sitter.getId() == SITTER_ID);

        //create pet
        Pet pet = petService.createPet(owner, "Max", 5, PetType.DOG, "1002344490");
        assertNotNull(pet);

        //create a request
        Request request = requestService
                .createRequest(owner, pet,
                        Pair.of(DateTime.parse("2016-03-05"),DateTime.parse("2016-03-06")), "");
        assertNotNull(request);
        assertTrue(request.getStartAt().before(request.getEndAt()));
    }

    @Test
    public void testCreateResponse(){
        //create a response
        Response response = operationsService.createResponse(SITTER_ID, REQUEST_ID);
        assertNotNull(response);
        assertNotNull(response.getRequest());

        // accept response
        operationsService.acceptResponse(REQUEST_ID,response.getId());
        assertTrue(response.getResponseStatus() == ResponseStatus.ACCEPTED);

        //close request
        Request request = requestService.findById(REQUEST_ID);

        Review review = reviewService.createReview(ReviewGrade.GOOD,
                "Max was well taken care of.");
        operationsService.closeRequest(REQUEST_ID, review.getId());

        assertTrue(request.getRequestStatus() == RequestStatus.SOLVED);
    }

}
