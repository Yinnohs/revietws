package com.yinnoh.reviwts.reviewWrite.reviews.application.dto;

public record CreateReviewRequest(
        String reviewedAccountId,
        String reviewerAccountId,
        double score,
        String title,
        String description
) {
}
