package com.yinnoh.reviwts.reviewRead.reviews.application.dto;

public record ReviewFilterRequest(
    String reviewedAccountId,
    String reviewerAccountId,
    Double minimumScore
) {}
