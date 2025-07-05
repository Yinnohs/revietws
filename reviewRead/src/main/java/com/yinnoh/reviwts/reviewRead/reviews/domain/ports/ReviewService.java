package com.yinnoh.reviwts.reviewRead.reviews.domain.ports;

import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> getAllReviews();
    Optional<Review> getReviewById(String id);
    List<Review> getReviewsByReviewedAccount(String reviewedAccountId);
    List<Review> getReviewsByReviewer(String reviewerAccountId);
    List<Review> getReviewsWithMinimumScore(double minimumScore);
}
