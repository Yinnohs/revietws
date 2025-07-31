package com.yinnohs.reviwts.reviewApi.reviews.mapper;

import com.google.protobuf.Timestamp;
import com.yinnohs.reviwts.reviewApi.reviews.dto.review.ReviewResponse;
import com.yinnohs.reviwts.reviewRead.reviews.infrastructure.grpc.GetReviewResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class ReviewMapper {
    public ReviewResponse fromGrpc(GetReviewResponse grpcResponse) {
        return new ReviewResponse(
                grpcResponse.getId(),
                grpcResponse.getReviewedAccountId(),
                grpcResponse.getReviewerAccountId(),
                grpcResponse.getScore(),
                grpcResponse.getTitle(),
                grpcResponse.getDescription(),
                fromTimestamp(grpcResponse.getCreatedAt()),
                fromTimestamp(grpcResponse.getUpdatedAt()),
                fromTimestamp(grpcResponse.getDeletedAt())
        );
    }

    private LocalDateTime fromTimestamp(Timestamp createdAt) {
        if (createdAt == null || createdAt.getSeconds() == 0) {
            return null;
        }
        return LocalDateTime.ofEpochSecond(createdAt.getSeconds(), createdAt.getNanos(), ZoneOffset.UTC);
    }

}
