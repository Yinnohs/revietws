package com.yinnohs.reviwts.reviewApi.reviews.dto;

public record CreateReviewRequestDTO(
        String reviewedAccountId,
        String reviewerAccountId,
        double score,
        String title,
        String description,
        String token
) {}

