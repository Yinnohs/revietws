package com.yinnoh.reviwts.reviewWrite.reviews.domain.ports.driver;

import com.yinnoh.reviwts.reviewWrite.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.event.Event;

public interface ReviewService {
    public Event<Review> createReview(Event<Review> reviewEvent);
}
