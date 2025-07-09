package com.yinnohs.reviwts.reviewApi.reviews.service;


import com.yinnohs.reviwts.reviewApi.reviews.dto.ReviewResponse;
import com.yinnohs.reviwts.reviewApi.reviews.mapper.ReviewMapper;
import com.yinnohs.reviwts.reviewRead.reviews.infrastructure.grpc.ListReviewsRequest;
import com.yinnohs.reviwts.reviewRead.reviews.infrastructure.grpc.ListReviewsResponse;
import com.yinnohs.reviwts.reviewRead.reviews.infrastructure.grpc.ReviewReadGrpcServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewReadCallerService {
    private final ReviewMapper mapper;

    public List<ReviewResponse> getAllReviews(){
        ManagedChannel readChannel = ManagedChannelBuilder
                .forAddress("localhost", 5152)
                .usePlaintext()
                .build();

        ReviewReadGrpcServiceGrpc.ReviewReadGrpcServiceBlockingStub readBlockingStub = ReviewReadGrpcServiceGrpc
                .newBlockingStub(readChannel);

        ListReviewsResponse response = readBlockingStub.listReviews(ListReviewsRequest.newBuilder().build());
        return response.getReviewsList().stream().map(mapper::fromGrpc).toList();
    }
}
