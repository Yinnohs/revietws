package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.service;

import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewRepository;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConcreteReviewService implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> getReviewById(String id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> getReviewsByReviewedAccount(String reviewedAccountId) {
        return reviewRepository.findByReviewedAccountId(reviewedAccountId);
    }

    @Override
    public List<Review> getReviewsByReviewer(String reviewerAccountId) {
        return reviewRepository.findByReviewerAccountId(reviewerAccountId);
    }

    @Override
    public List<Review> getReviewsWithMinimumScore(double minimumScore) {
        return reviewRepository.findByScoreGreaterThan(minimumScore);
    }
}

