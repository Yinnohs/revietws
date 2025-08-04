package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.service;

import com.yinnoh.reviwts.reviewWrite.reviews.application.CreateReviewUseCase;
import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.mapper.ReviewWriteGrpcServiceMapper;
import com.yinnohs.reviwts.reviewWrite.reviews.infrastructure.grpc.CreateReviewRequest;
import com.yinnohs.reviwts.reviewWrite.reviews.infrastructure.grpc.CreateReviewResponse;
import com.yinnohs.reviwts.reviewWrite.reviews.infrastructure.grpc.ReviewWriteGrpcServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
public class GrpcReviewWriteService extends ReviewWriteGrpcServiceGrpc.ReviewWriteGrpcServiceImplBase {

    private final CreateReviewUseCase useCase;
    private final ReviewWriteGrpcServiceMapper mapper;

    @Override
    public void createReview(CreateReviewRequest request, StreamObserver<CreateReviewResponse> responseObserver) {

        try{
            useCase.execute(mapper.toCreateReviewRequest(request));
        } catch (RuntimeException e) {
            responseObserver.onError(e);
        }
        responseObserver.onNext(CreateReviewResponse.newBuilder()
                        .setMessage("Review created successfully")
                .build());
        responseObserver.onCompleted();
    }
}