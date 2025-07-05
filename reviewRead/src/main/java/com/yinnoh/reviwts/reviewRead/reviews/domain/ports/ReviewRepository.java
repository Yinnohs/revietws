package com.yinnoh.reviwts.reviewRead.reviews.domain.ports;

import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    List<Review> findAll();
    Optional<Review> findById(String id);
    List<Review> findByReviewedAccountId(String reviewedAccountId);
    List<Review> findByReviewerAccountId(String reviewerAccountId);
    List<Review> findByScoreGreaterThan(double score);
}
