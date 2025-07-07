package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.mapper;

import com.yinnoh.reviwts.reviewWrite.reviews.application.dto.CreateReviewRequest;
import org.springframework.stereotype.Component;

@Component
public class ReviewWriteGrpcServiceMapper {
    public CreateReviewRequest toCreateReviewRequest(com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.grpc.CreateReviewRequest request) {
        return new CreateReviewRequest(
                request.getReviewedAccountId(),
                request.getReviewerAccountId(),
                request.getScore(),
                request.getTitle(),
                request.getDescription());

    }
}
