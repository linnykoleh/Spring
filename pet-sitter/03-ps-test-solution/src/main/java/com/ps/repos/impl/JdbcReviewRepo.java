package com.ps.repos.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ps.ents.Request;
import com.ps.ents.Response;
import com.ps.ents.Review;
import com.ps.ents.User;
import com.ps.repos.ReviewRepo;

@Repository("reviewRepo")
public class JdbcReviewRepo extends JdbcAbstractRepo<Review> implements ReviewRepo {

    @Autowired
    public JdbcReviewRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Set<Review> findAllForUser(User user) {
        return null;
    }

    @Override
    public Set<Review> findAllForRequest(Request request) {
        return null;
    }

    @Override
    public Set<Review> findAllForResponse(Response response) {
        return null;
    }
}
