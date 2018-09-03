package com.ps.services;

import com.ps.base.ReviewGrade;
import com.ps.ents.Review;
import com.ps.ents.User;

import java.util.Set;

public interface ReviewService  extends AbstractService<Review>{

    Review createReview(ReviewGrade grade, String details);

    Set<Review> findAllByUser(User user);

}
