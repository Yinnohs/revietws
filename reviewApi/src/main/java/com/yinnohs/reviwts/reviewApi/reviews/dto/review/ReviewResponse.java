package com.yinnohs.reviwts.reviewApi.reviews.dto.review;

import java.time.LocalDateTime;

public record ReviewResponse(
    String id,
    String reviewedAccountId,
    String reviewerAccountId,
    double score,
    String title,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) {}
