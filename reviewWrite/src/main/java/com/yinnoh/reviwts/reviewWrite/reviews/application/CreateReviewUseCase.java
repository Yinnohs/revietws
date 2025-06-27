package com.yinnoh.reviwts.reviewWrite.reviews.application;

import com.yinnoh.reviwts.reviewWrite.reviews.application.dto.CreateReviewRequest;
import com.yinnoh.reviwts.reviewWrite.reviews.application.event.CreateReviewEvent;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.event.Event;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.ports.driver.EventSender;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.ports.driver.ReviewService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class CreateReviewUseCase {
    private final ReviewService reviewService;
    private final EventSender<Review> eventSender;

    public void execute(CreateReviewRequest request){
        Review newReview = Review.builder()
                .id(UUID.randomUUID().toString())
                .reviewedAccountId(request.reviewedAccountId())
                .reviewerAccountId(request.reviewerAccountId())
                .title(request.title())
                .description(request.description())
                .score(request.score())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        CreateReviewEvent event = CreateReviewEvent.createNewEvent(newReview);
        Event<Review> createdReview = reviewService.createReview(event);

        //EventHandler<Review> handler = new EventHandler<>(eventSender);
        //handler.sendEvent(event);
    }
}
