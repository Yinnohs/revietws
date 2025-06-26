package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "reviews")
public class ReviewModel {
    @Id
    String id;
    @Column(nullable = false)
    String reviewedAccountId;
    @Column(nullable = false)
    String reviewerAccountId;
    @Column(nullable = false)
    double score;
    String title;
    String description;
    @Column(nullable = false)
    LocalDateTime createdAt;
    @Column(nullable = false)
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;
}
