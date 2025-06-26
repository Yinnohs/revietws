package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.service;

import com.yinnoh.reviwts.reviewWrite.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.ports.driver.ReviewService;
import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.mapper.ReviewMapper;
import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.model.ReviewModel;
import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewConcreteService implements ReviewService {

    private final ReviewRepository repository;
    private final ReviewMapper mapper;

    @Override
    public Review createReview(Review review) {
        ReviewModel reviewToSave = mapper.domainToModel(review);
        ReviewModel savedReview = repository.save(reviewToSave);
        return mapper.modelToDomain(savedReview);
    }
}
