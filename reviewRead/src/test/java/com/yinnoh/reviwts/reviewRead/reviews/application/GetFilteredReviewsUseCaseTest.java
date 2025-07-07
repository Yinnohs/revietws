package com.yinnoh.reviwts.reviewRead.reviews.application;

import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewFilterRequest;
import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.mapper.ReviewMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetFilteredReviewsUseCaseTest {
    private ReviewService reviewService;
    private ReviewMapper reviewMapper;
    private GetFilteredReviewsUseCase useCase;

    @BeforeEach
    void setUp() {
        reviewService = mock(ReviewService.class);
        reviewMapper = mock(ReviewMapper.class);
        useCase = new GetFilteredReviewsUseCase(reviewService, reviewMapper);
    }

    @Test
    void returnsFilteredByReviewedAccount() {
        Review review = Review.builder().id("1").reviewedAccountId("acc1").score(5).build();
        ReviewResponse response = new ReviewResponse("1", "acc1", null, 5, null, null, null, null, null);
        when(reviewService.getReviewsByReviewedAccount("acc1")).thenReturn(List.of(review));
        when(reviewMapper.toResponse(review)).thenReturn(response);
        ReviewFilterRequest filter = new ReviewFilterRequest("acc1", null, null);
        List<ReviewResponse> result = useCase.execute(filter);
        assertEquals(1, result.size());
        assertEquals("acc1", result.get(0).reviewedAccountId());
    }

    @Test
    void returnsFilteredByReviewer() {
        Review review = Review.builder().id("2").reviewerAccountId("acc2").score(4).build();
        ReviewResponse response = new ReviewResponse("2", null, "acc2", 4, null, null, null, null, null);
        when(reviewService.getReviewsByReviewer("acc2")).thenReturn(List.of(review));
        when(reviewMapper.toResponse(review)).thenReturn(response);
        ReviewFilterRequest filter = new ReviewFilterRequest(null, "acc2", null);
        List<ReviewResponse> result = useCase.execute(filter);
        assertEquals(1, result.size());
        assertEquals("acc2", result.get(0).reviewerAccountId());
    }

    @Test
    void returnsFilteredByMinimumScore() {
        Review review = Review.builder().id("3").score(5).build();
        ReviewResponse response = new ReviewResponse("3", null, null, 5, null, null, null, null, null);
        when(reviewService.getAllReviews()).thenReturn(List.of(review));
        when(reviewMapper.toResponse(review)).thenReturn(response);
        ReviewFilterRequest filter = new ReviewFilterRequest(null, null, 4.0);
        List<ReviewResponse> result = useCase.execute(filter);
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).score());
    }

    @Test
    void returnsEmptyIfNoMatch() {
        when(reviewService.getAllReviews()).thenReturn(List.of());
        ReviewFilterRequest filter = new ReviewFilterRequest(null, null, 10.0);
        List<ReviewResponse> result = useCase.execute(filter);
        assertTrue(result.isEmpty());
    }
}

