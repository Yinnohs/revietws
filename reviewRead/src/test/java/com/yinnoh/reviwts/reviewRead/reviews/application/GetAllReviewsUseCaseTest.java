package com.yinnoh.reviwts.reviewRead.reviews.application;

import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.mapper.ReviewMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetAllReviewsUseCaseTest {
    private ReviewService reviewService;
    private ReviewMapper reviewMapper;
    private GetAllReviewsUseCase useCase;

    @BeforeEach
    void setUp() {
        reviewService = mock(ReviewService.class);
        reviewMapper = mock(ReviewMapper.class);
        useCase = new GetAllReviewsUseCase(reviewService, reviewMapper);
    }

    @Test
    void returnsMappedResponses() {
        Review review = Review.builder().id("1").build();
        ReviewResponse response = new ReviewResponse("1", null, null, 0, null, null, null, null, null);
        when(reviewService.getAllReviews()).thenReturn(List.of(review));
        when(reviewMapper.toResponse(review)).thenReturn(response);
        List<ReviewResponse> result = useCase.execute();
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).id());
    }

    @Test
    void returnsEmptyListIfNoReviews() {
        when(reviewService.getAllReviews()).thenReturn(List.of());
        List<ReviewResponse> result = useCase.execute();
        assertTrue(result.isEmpty());
    }
}

