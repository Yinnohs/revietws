package com.yinnoh.reviwts.reviewWrite.reviews.application.event;

import com.yinnoh.reviwts.reviewWrite.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.event.Event;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateReviewEvent extends Event<Review> {

    public CreateReviewEvent(String eventId,
                             String eventName,
                             Review data,
                             LocalDateTime createdAt,
                             LocalDateTime updatedAt,
                             boolean processed) {
        super(eventId, eventName, data, createdAt, updatedAt, processed);
    }

    public static CreateReviewEvent createNewEvent(Review data){
        String eventName = CreateReviewEvent.class.getName();
        eventName = eventName.substring(eventName.lastIndexOf('.') + 1);
        String eventId = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        return new CreateReviewEvent(eventId,eventName,data,now,now,false);
    }

    public static CreateReviewEvent createEvent(String eventId,
                                                String eventName,
                                                Review data,
                                                LocalDateTime createdAt,
                                                LocalDateTime updatedAt,
                                                boolean processed) {
        return new CreateReviewEvent(eventId, eventName, data, createdAt, updatedAt, processed);
    }
}
