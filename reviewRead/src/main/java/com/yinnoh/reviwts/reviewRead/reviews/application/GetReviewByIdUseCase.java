package com.yinnoh.reviwts.reviewRead.reviews.application;

import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class GetReviewByIdUseCase {
    private final ReviewService reviewService;

    public Optional<ReviewResponse> execute(String id) {
        return reviewService.getReviewById(id)
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
            ));
    }
}

