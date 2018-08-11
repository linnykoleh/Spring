package com.ps.repo.stub;

import java.util.Set;

import org.apache.commons.lang3.NotImplementedException;

import com.ps.ents.Request;
import com.ps.ents.Response;
import com.ps.ents.Review;
import com.ps.ents.User;
import com.ps.repos.ReviewRepo;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class StubReviewRepo extends StubAbstractRepo<Review> implements ReviewRepo {

    @Override
    public Set<Review> findAllForUser(User user) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<Review> findAllForRequest(Request request) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<Review> findAllForResponse(Response response) {
        throw new NotImplementedException("Not needed for this stub.");
    }
}
