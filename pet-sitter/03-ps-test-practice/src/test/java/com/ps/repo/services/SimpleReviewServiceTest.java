package com.ps.repo.services;

import com.ps.base.UserType;
import com.ps.ents.Request;
import com.ps.ents.Review;
import com.ps.ents.User;
import com.ps.repos.ReviewRepo;
import com.ps.services.impl.SimpleReviewService;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.ps.util.TestObjectsBuilder.buildUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class SimpleReviewServiceTest {

    private static final Long REVIEW_ID = 1L;

    private ReviewRepo reviewMockRepo = mock(ReviewRepo.class);

    private SimpleReviewService simpleReviewService;

    @Before
    public void setUp() {
        simpleReviewService = new SimpleReviewService();
        simpleReviewService.setRepo(reviewMockRepo);
    }

    @Test
    public void findByIdPositive() {
        final Review review = new Review();
        review.setId(REVIEW_ID);
        when(reviewMockRepo.findById(REVIEW_ID)).thenReturn(review);

        final Review result = simpleReviewService.findById(REVIEW_ID);
        assertNotNull(result);
        assertEquals(review.getId(), result.getId());
    }

    @Test
    public void findByUserPositive() {
        final User user = buildUser("gigi@gmail.com", "1!2#tre", UserType.OWNER);
        final Set<Review> reviewSet = prepareReview(user);

        when(reviewMockRepo.findAllForUser(anyObject())).thenReturn(reviewSet);

        Set<Review> result = simpleReviewService.findAllByUser(user);
        verify(reviewMockRepo, times(1)).findAllForUser(user);
        assertEquals(result.size(), 1);
    }

    private Set<Review> prepareReview(User user) {
        final Request req = new Request();
        req.setUser(user);

        final Review review = new Review();
        review.setRequest(req);

        final Set<Review> reviewSet = new HashSet<>();
        reviewSet.add(review);
        return reviewSet;
    }
}
