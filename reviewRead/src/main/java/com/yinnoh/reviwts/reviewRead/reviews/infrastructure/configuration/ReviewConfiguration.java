package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.configuration;

import com.yinnoh.reviwts.reviewRead.reviews.application.GetAllReviewsUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.application.GetFilteredReviewsUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.application.GetReviewByIdUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewConfiguration {
    @Bean
    public GetAllReviewsUseCase getAllReviewsUseCase(ReviewService reviewService) {
        return new GetAllReviewsUseCase(reviewService);
    }
    @Bean
    public GetReviewByIdUseCase getReviewByIdUseCase(ReviewService reviewService) {
        return new GetReviewByIdUseCase(reviewService);
    }
    @Bean
    public GetFilteredReviewsUseCase getFilteredReviewsUseCase(ReviewService reviewService) {
        return new GetFilteredReviewsUseCase(reviewService);
    }
}

