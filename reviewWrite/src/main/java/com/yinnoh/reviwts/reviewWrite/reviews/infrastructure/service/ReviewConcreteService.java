package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.service;

import com.yinnoh.reviwts.reviewWrite.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.ports.driver.ReviewService;
import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.mapper.ReviewMapper;
import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.model.ReviewEventModel;
import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.repository.ReviewEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewConcreteService implements ReviewService {

    private final ReviewEventRepository repository;
    private final ReviewMapper mapper;

    @Override
    public Review createReview(Review review) {
        ReviewEventModel eventToSave = ReviewEventModel.builder().build();
        repository.save(eventToSave);
        return review;
    }
}
