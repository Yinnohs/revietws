package com.yinnoh.reviwts.reviewRead.reviews.application.dto;

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
