package com.yinnoh.reviwts.reviewRead.reviews.application;

import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.mapper.ReviewMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetReviewByIdUseCaseTest {
    private ReviewService reviewService;
    private ReviewMapper reviewMapper;
    private GetReviewByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        reviewService = mock(ReviewService.class);
        reviewMapper = mock(ReviewMapper.class);
        useCase = new GetReviewByIdUseCase(reviewService, reviewMapper);
    }

    @Test
    void returnsMappedResponseIfFound() {
        Review review = Review.builder().id("1").build();
        ReviewResponse response = new ReviewResponse("1", null, null, 0, null, null, null, null, null);
        when(reviewService.getReviewById("1")).thenReturn(Optional.of(review));
        when(reviewMapper.toResponse(review)).thenReturn(response);
        Optional<ReviewResponse> result = useCase.execute("1");
        assertTrue(result.isPresent());
        assertEquals("1", result.get().id());
    }

    @Test
    void returnsEmptyIfNotFound() {
        when(reviewService.getReviewById("2")).thenReturn(Optional.empty());
        Optional<ReviewResponse> result = useCase.execute("2");
        assertTrue(result.isEmpty());
    }
}

