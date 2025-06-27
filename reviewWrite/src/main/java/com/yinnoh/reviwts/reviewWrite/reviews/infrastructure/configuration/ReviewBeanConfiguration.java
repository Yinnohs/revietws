package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.configuration;

import com.yinnoh.reviwts.reviewWrite.reviews.application.CreateReviewUseCase;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.ports.driver.EventSender;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.ports.driver.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ReviewBeanConfiguration {

    private final ReviewService reviewService;
    private final EventSender<Review> eventSender = null;

    @Bean
    public CreateReviewUseCase createReviewUseCase(){
        return new CreateReviewUseCase(reviewService, eventSender);
    }
}
