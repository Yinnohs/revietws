package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.configuration;

import com.yinnoh.reviwts.reviewRead.reviews.application.GetAllReviewsUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.application.GetFilteredReviewsUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.application.GetReviewByIdUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.mapper.ReviewMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewConfiguration {
    @Bean
    public GetAllReviewsUseCase getAllReviewsUseCase(ReviewService reviewService, ReviewMapper mapper) {
        return new GetAllReviewsUseCase(reviewService, mapper);
    }
    @Bean
    public GetReviewByIdUseCase getReviewByIdUseCase(ReviewService reviewService, ReviewMapper mapper) {
        return new GetReviewByIdUseCase(reviewService, mapper);
    }
    @Bean
    public GetFilteredReviewsUseCase getFilteredReviewsUseCase(ReviewService reviewService, ReviewMapper mapper) {
        return new GetFilteredReviewsUseCase(reviewService, mapper);
    }
}

