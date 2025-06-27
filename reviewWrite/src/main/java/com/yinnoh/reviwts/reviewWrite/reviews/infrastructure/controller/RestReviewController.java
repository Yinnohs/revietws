package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.controller;

import com.yinnoh.reviwts.reviewWrite.reviews.application.CreateReviewUseCase;
import com.yinnoh.reviwts.reviewWrite.reviews.application.dto.CreateReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")
public class RestReviewController {
    private final CreateReviewUseCase createReviewUseCase;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody CreateReviewRequest request){
        createReviewUseCase.execute(request);
        return ResponseEntity.ok("Request Created");
    }

}
