package com.yinnoh.reviwts.reviewRead.reviews.application;

import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewFilterRequest;
import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class GetFilteredReviewsUseCase {
    private final ReviewService reviewService;

    public List<ReviewResponse> execute(ReviewFilterRequest filter) {
        Stream<Review> reviewStream = reviewService.getAllReviews().stream();

        if (filter.reviewedAccountId() != null) {
            reviewStream = reviewService.getReviewsByReviewedAccount(filter.reviewedAccountId()).stream();
        } else if (filter.reviewerAccountId() != null) {
            reviewStream = reviewService.getReviewsByReviewer(filter.reviewerAccountId()).stream();
        }

        if (filter.minimumScore() != null) {
            reviewStream = reviewStream.filter(review -> review.getScore() >= filter.minimumScore());
        }

        return reviewStream
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

