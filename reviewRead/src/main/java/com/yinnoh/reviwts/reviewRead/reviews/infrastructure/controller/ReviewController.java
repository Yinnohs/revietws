package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.controller;

import com.yinnoh.reviwts.reviewRead.reviews.application.GetAllReviewsUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.application.GetFilteredReviewsUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.application.GetReviewByIdUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewFilterRequest;
import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final GetAllReviewsUseCase getAllReviewsUseCase;
    private final GetReviewByIdUseCase getReviewByIdUseCase;
    private final GetFilteredReviewsUseCase getFilteredReviewsUseCase;

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAllReviews() {
        List<ReviewResponse> reviews = getAllReviewsUseCase.execute();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable String id) {
        return getReviewByIdUseCase.execute(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ReviewResponse>> getFilteredReviews(
            @RequestParam(required = false) String reviewedAccountId,
            @RequestParam(required = false) String reviewerAccountId,
            @RequestParam(required = false) Double minimumScore) {
        ReviewFilterRequest filter = new ReviewFilterRequest(
            reviewedAccountId, reviewerAccountId, minimumScore);
        List<ReviewResponse> reviews = getFilteredReviewsUseCase.execute(filter);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByAccount(@PathVariable String accountId) {
        ReviewFilterRequest filter = new ReviewFilterRequest(accountId, null, null);
        List<ReviewResponse> reviews = getFilteredReviewsUseCase.execute(filter);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/reviewer/{reviewerId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByReviewer(@PathVariable String reviewerId) {
        ReviewFilterRequest filter = new ReviewFilterRequest(null, reviewerId, null);
        List<ReviewResponse> reviews = getFilteredReviewsUseCase.execute(filter);
        return ResponseEntity.ok(reviews);
    }
}

