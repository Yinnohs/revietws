package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.mapper;

import com.google.protobuf.Timestamp;
import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.grpc.GetReviewResponse;
import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.model.ReviewModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

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

    public ReviewModel toModel(Review entity) {
        if (entity == null) return null;
        return ReviewModel.builder()
            .id(entity.getId())
            .reviewedAccountId(entity.getReviewedAccountId())
            .reviewerAccountId(entity.getReviewerAccountId())
            .score(entity.getScore())
            .title(entity.getTitle())
            .description(entity.getDescription())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .deletedAt(entity.getDeletedAt())
            .build();
    }

    public ReviewResponse toResponse(Review entity) {
        if (entity == null) return null;
        return new ReviewResponse(
            entity.getId(),
            entity.getReviewedAccountId(),
            entity.getReviewerAccountId(),
            entity.getScore(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getDeletedAt()
        );
    }

    public Review toDomainFromResponse(ReviewResponse dto) {
        if (dto == null) return null;
        return Review.builder()
            .id(dto.id())
            .reviewedAccountId(dto.reviewedAccountId())
            .reviewerAccountId(dto.reviewerAccountId())
            .score(dto.score())
            .title(dto.title())
            .description(dto.description())
            .createdAt(dto.createdAt())
            .updatedAt(dto.updatedAt())
            .deletedAt(dto.deletedAt())
            .build();
    }

    public GetReviewResponse toGrpc(ReviewResponse review) {
        return GetReviewResponse.newBuilder()
                .setId(review.id())
                .setReviewedAccountId(review.reviewedAccountId())
                .setReviewerAccountId(review.reviewerAccountId())
                .setScore(review.score())
                .setTitle(review.title())
                .setDescription(review.description())
                .setCreatedAt(toTimestamp(review.createdAt()))
                .setUpdatedAt(toTimestamp(review.updatedAt()))
                .setDeletedAt(toTimestamp(review.deletedAt()))
                .build();
    }


    private Timestamp toTimestamp(LocalDateTime time) {
        if (time == null) return Timestamp.getDefaultInstance();
        return Timestamp.newBuilder()
                .setSeconds(time.toEpochSecond(ZoneOffset.UTC))
                .setNanos(time.getNano())
                .build();
    }
}
