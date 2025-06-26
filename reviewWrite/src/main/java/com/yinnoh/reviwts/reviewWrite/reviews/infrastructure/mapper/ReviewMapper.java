package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.mapper;

import com.yinnoh.reviwts.reviewWrite.reviews.application.dto.CreateReviewRequest;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.model.ReviewModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ReviewMapper {
    Review createRequestToReview(CreateReviewRequest requests);
    ReviewModel domainToModel(Review review);
    Review modelToDomain(ReviewModel model);
}
