package com.yinnoh.reviwts.reviewWrite.reviews.application;

import com.yinnoh.reviwts.reviewWrite.reviews.application.dto.CreateReviewRequest;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.ports.driven.ReviewService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateReviewUseCase {
    private final ReviewService reviewService;

    public void execute(CreateReviewRequest request){
        /*Review newReview = Review.builder()
                .id(UUID.randomUUID().toString())
                .reviewedAccountId(request.reviewedAccountId())
                .reviewerAccountId(request.reviewerAccountId())
                .title(request.title())
                .description(request.description())
                .score(request.score())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Review createdReview = reviewService.createReview(newReview);
        */
        throw new RuntimeException("Not implemented");
    }
}
