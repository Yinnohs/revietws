package com.yinnohs.reviwts.reviewApi.reviews.service.review;

import com.yinnohs.reviwts.reviewApi.reviews.dto.review.CreateReviewRequestDTO;
import com.yinnohs.reviwts.reviewWrite.reviews.infrastructure.grpc.CreateReviewRequest;
import com.yinnohs.reviwts.reviewWrite.reviews.infrastructure.grpc.ReviewWriteGrpcServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class ReviewWriteCallerService {

    public String createReview(CreateReviewRequestDTO request) {

        var requestGrpc = CreateReviewRequest.newBuilder()
                .setReviewedAccountId(request.reviewedAccountId())
                .setReviewerAccountId(request.reviewerAccountId())
                .setScore(request.score())
                .setTitle(request.title())
                .setDescription(request.description())
                .build();

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5251)
                .usePlaintext()
                .build();

        ReviewWriteGrpcServiceGrpc.ReviewWriteGrpcServiceBlockingStub writeStub = ReviewWriteGrpcServiceGrpc.newBlockingStub(channel);

        var response = writeStub.createReview(requestGrpc);
        return response.getMessage();
    }

}
