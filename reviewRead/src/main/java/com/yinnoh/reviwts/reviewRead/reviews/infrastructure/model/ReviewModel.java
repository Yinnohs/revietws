package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "reviews")
public class ReviewModel {
    @Id
    private String id;
    @NonNull
    private String reviewedAccountId;
    @NonNull
    private String reviewerAccountId;
    private double score;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private LocalDateTime createdAt;
    @NonNull
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

