package com.yinnoh.reviwts.reviewRead.reviews.application;

import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class GetAllReviewsUseCase {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public List<ReviewResponse> execute() {
        return reviewService.getAllReviews()
            .stream()
            .map(reviewMapper::toResponse)
            .toList();
    }
}
