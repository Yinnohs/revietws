package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.mapper;

import com.yinnoh.reviwts.reviewWrite.reviews.application.dto.CreateReviewRequest;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.entity.Review;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ReviewMapper {
    Review createRequestToReview(CreateReviewRequest requests);
}
