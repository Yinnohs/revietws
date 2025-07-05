package com.yinnohs.reviwts.reviewProcesor.infrastructure;

import com.yinnohs.reviwts.reviewProcesor.application.dto.IncomingReviewEvent;
import com.yinnohs.reviwts.reviewProcesor.domain.ports.driven.ReviewEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewEventProcessingService  implements ReviewEventService {

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    @Override
    public void processReviewEvent(IncomingReviewEvent reviewEvent) {
        ReviewModel persistentReview = reviewMapper.mapToReviewModel(reviewEvent);
        reviewRepository.save(persistentReview);
    }
}
