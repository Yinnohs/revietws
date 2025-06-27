package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.mapper;

import com.yinnoh.reviwts.reviewWrite.reviews.application.event.CreateReviewEvent;
import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.model.ReviewEventModel;
import org.springframework.stereotype.Component;

@Component
public class ReviewEventMapper {
     public CreateReviewEvent reviewEventModelToCreateReviewEvent(ReviewEventModel eventModel){
          return CreateReviewEvent.createEvent(eventModel.getEventId(),
                  eventModel.getEventName(),
                  eventModel.getData(),
                  eventModel.getCreatedAt(),
                  eventModel.getUpdatedAt(),
                  eventModel.isProcessed());
     }
}
