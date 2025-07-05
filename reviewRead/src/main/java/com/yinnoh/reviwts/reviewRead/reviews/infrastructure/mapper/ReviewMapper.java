package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.mapper;

import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.model.ReviewModel;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public Review toDomain(ReviewModel model) {
        if (model == null) return null;
        return Review.builder()
            .id(model.getId())
            .reviewedAccountId(model.getReviewedAccountId())
            .reviewerAccountId(model.getReviewerAccountId())
            .score(model.getScore())
            .title(model.getTitle())
            .description(model.getDescription())
            .createdAt(model.getCreatedAt())
            .updatedAt(model.getUpdatedAt())
            .deletedAt(model.getDeletedAt())
            .build();
    }
}

