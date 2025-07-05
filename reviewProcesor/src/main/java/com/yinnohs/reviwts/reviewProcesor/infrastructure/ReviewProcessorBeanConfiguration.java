package com.yinnohs.reviwts.reviewProcesor.infrastructure;

import com.yinnohs.reviwts.reviewProcesor.application.usecase.ProcessCreateReviewUseCase;
import com.yinnohs.reviwts.reviewProcesor.domain.ports.driven.ReviewEventService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ReviewProcessorBeanConfiguration {
    // This class can be used to define additional beans or configurations
    // related to the review processor module if needed in the future.
    @Bean
    public ProcessCreateReviewUseCase processCreateReviewUseCase(ReviewEventService reviewEventService){
        return new ProcessCreateReviewUseCase(reviewEventService);
    }
}
