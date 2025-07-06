package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.service;

import com.yinnoh.reviwts.reviewRead.reviews.application.GetAllReviewsUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.application.GetReviewByIdUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.grpc.*;
import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.mapper.ReviewMapper;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Optional;

@GrpcService
@AllArgsConstructor
public class ReviewGRPCConcreteService extends ReviewGrpcServiceGrpc.ReviewGrpcServiceImplBase {

    private final GetAllReviewsUseCase getAllReviewsUseCase;
    private final GetReviewByIdUseCase getReviewByIdUseCase;
    private final ReviewMapper mapper;


    @Override
    public void getReview(GetReviewRequest request, StreamObserver<GetReviewResponse> responseObserver) {
        Optional<ReviewResponse> reviewResponse = getReviewByIdUseCase.execute(request.getReviewId());
        if (reviewResponse.isEmpty()) responseObserver.onError(new Throwable("Review not found"));
        GetReviewResponse response = mapper.toGrpc(reviewResponse.get());
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listReviews(ListReviewsRequest request, StreamObserver<ListReviewsResponse> responseObserver) {
        List<ReviewResponse> useCaseResponse = getAllReviewsUseCase.execute();
        List<GetReviewResponse> grpcResponses = useCaseResponse.stream()
                .map(mapper::toGrpc)
                .toList();
        ListReviewsResponse response = ListReviewsResponse.newBuilder()
                .addAllReviews(grpcResponses)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

