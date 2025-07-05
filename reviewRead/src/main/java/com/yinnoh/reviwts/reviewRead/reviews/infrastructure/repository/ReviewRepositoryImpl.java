package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.repository;

import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewRepository;
import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
    private final MongoReviewRepository mongoRepository;
    private final ReviewMapper mapper;

    @Override
    public List<Review> findAll() {
        return mongoRepository.findAll()
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public Optional<Review> findById(String id) {
        return mongoRepository.findById(id)
            .map(mapper::toDomain);
    }

    @Override
    public List<Review> findByReviewedAccountId(String reviewedAccountId) {
        return mongoRepository.findByReviewedAccountId(reviewedAccountId)
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public List<Review> findByReviewerAccountId(String reviewerAccountId) {
        return mongoRepository.findByReviewerAccountId(reviewerAccountId)
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public List<Review> findByScoreGreaterThan(double score) {
        return mongoRepository.findByScoreGreaterThanEqual(score)
            .stream()
            .map(mapper::toDomain)
            .toList();
    }
}

