package com.yinnohs.reviwts.reviewApi.reviews.controller;

import com.yinnohs.reviwts.reviewApi.reviews.dto.review.CreateReviewRequestDTO;
import com.yinnohs.reviwts.reviewApi.reviews.dto.review.ReviewResponse;
import com.yinnohs.reviwts.reviewApi.reviews.service.review.ReviewReadCallerService;
import com.yinnohs.reviwts.reviewApi.reviews.service.review.ReviewWriteCallerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewWriteCallerService reviewWriteCallerService;
    private final ReviewReadCallerService reviewReadCallerService;

    @PostMapping
    public ResponseEntity<String> createReview(CreateReviewRequestDTO request) {
        String response = reviewWriteCallerService.createReview(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAllReviews() {
        List<ReviewResponse> reviews = reviewReadCallerService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }
}
