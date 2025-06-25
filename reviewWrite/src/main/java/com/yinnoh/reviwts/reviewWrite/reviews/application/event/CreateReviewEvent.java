package com.yinnoh.reviwts.reviewWrite.reviews.application.event;

import com.yinnoh.reviwts.reviewWrite.reviews.domain.entity.Review;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateReviewEvent extends Event<Review> {

    public CreateReviewEvent(Review data) {
        super.data = data;
        super.eventId = UUID.randomUUID().toString();
        super.createdAt = LocalDateTime.now();
        super.EventName = this.getClass().getName();
    }

    @Override
    public void executeEvent() {
        throw new RuntimeException("not implemented");
    }
}
