package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.service;

import com.yinnoh.reviwts.reviewWrite.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.event.Event;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.ports.driver.ReviewService;
import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.mapper.ReviewEventMapper;
import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.model.ReviewEventModel;
import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.repository.ReviewEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ReviewConcreteService implements ReviewService {

    private final ReviewEventRepository repository;
    private final ReviewEventMapper mapper;
    @Override
    public Event<Review> createReview(Event<Review> reviewEvent) {
        LocalDateTime now = LocalDateTime.now();
        ReviewEventModel eventToSave = ReviewEventModel.builder()
                .eventId(reviewEvent.getEventId())
                .data(reviewEvent.getData())
                .entityId(reviewEvent.getEntityId())
                .eventName(reviewEvent.getEventName())
                .createdAt(now)
                .updatedAt(now)
                .processed(false)
                .build();
        return mapper.reviewEventModelToCreateReviewEvent(repository.save(eventToSave));
    }

}
