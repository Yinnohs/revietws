package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.configuration;

import com.yinnoh.reviwts.reviewWrite.reviews.domain.ports.driver.ReviewService;
import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.service.ReviewConcreteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ReviewBeanConfiguration {

    private final ReviewConcreteService reviewService;

    @Bean
    public ReviewService reviewService(){
        return reviewService;
    }
}
