package com.ps.services.impl;

import com.ps.base.ReviewGrade;
import com.ps.ents.Review;
import com.ps.repos.ReviewRepo;
import com.ps.services.ReviewService;

public class SimpleReviewService extends SimpleAbstractService<Review> implements ReviewService {

    private ReviewRepo repo;

    @Override
    public Review createReview(ReviewGrade grade, String details) {
        Review review = new Review();
        review.setGrade(grade);
        review.setDetails(details);
        repo.save(review);
        return review;
    }

    public void setRepo(ReviewRepo reviewRepo) {
        this.repo = reviewRepo;
    }

    @Override
    public ReviewRepo getRepo() {
        return repo;
    }
}
