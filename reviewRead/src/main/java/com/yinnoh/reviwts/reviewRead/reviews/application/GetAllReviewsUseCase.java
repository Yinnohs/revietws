package com.yinnoh.reviwts.reviewRead.reviews.application;

import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class GetAllReviewsUseCase {
    private final ReviewService reviewService;

    public List<ReviewResponse> execute() {
        return reviewService.getAllReviews()
            .stream()
            .map(review -> new ReviewResponse(
                review.getId(),
                review.getReviewedAccountId(),
                review.getReviewerAccountId(),
                review.getScore(),
                review.getTitle(),
                review.getDescription(),
                review.getCreatedAt(),
                review.getUpdatedAt(),
                review.getDeletedAt()
            ))
            .toList();
    }
}
