package com.ps.services;

import com.ps.base.ReviewGrade;
import com.ps.ents.Review;

public interface ReviewService {

    Review createReview(ReviewGrade grade, String details);

}
