package com.yinnohs.reviwts.reviewApi.reviews.controller;

import com.yinnohs.reviwts.reviewApi.reviews.dto.CreateReviewRequestDTO;
import com.yinnohs.reviwts.reviewApi.reviews.service.ReviewWriteCallerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewWriteCallerService reviewWriteCallerService;

    @PostMapping
    public ResponseEntity<String> createReview(CreateReviewRequestDTO request) {
        String response = reviewWriteCallerService.createReview(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
