package com.yinnoh.reviwts.reviewRead.reviews.application;

import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class GetReviewByIdUseCase {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public Optional<ReviewResponse> execute(String id) {
        return reviewService.getReviewById(id)
            .map(reviewMapper::toResponse);
    }
}
