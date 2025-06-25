package com.yinnoh.reviwts.reviewWrite.reviews.domain.ports.driven;

import com.yinnoh.reviwts.reviewWrite.reviews.domain.entity.Review;

public interface ReviewService {
    public Review createReview(Review review);
}
