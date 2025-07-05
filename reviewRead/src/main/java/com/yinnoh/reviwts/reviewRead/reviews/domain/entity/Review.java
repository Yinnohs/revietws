package com.yinnoh.reviwts.reviewRead.reviews.domain.entity;

import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Review {
    private String id;
    private String reviewedAccountId;
    private String reviewerAccountId;
    private double score;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
