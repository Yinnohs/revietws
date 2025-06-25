package com.yinnoh.reviwts.reviewWrite.reviews.domain.entity;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Review {
    String id;
    String reviewedAccountId;
    String reviewerAccountId;
    double score;
    String title;
    String description;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;
}
