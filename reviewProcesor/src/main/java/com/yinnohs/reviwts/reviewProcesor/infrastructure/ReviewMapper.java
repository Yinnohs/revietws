package com.yinnohs.reviwts.reviewProcesor.infrastructure;

import com.yinnohs.reviwts.reviewProcesor.application.dto.IncomingReviewEvent;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    /**
     * Maps an incoming review event to a ReviewEntity.
     *
     * @param reviewEvent the incoming review event
     * @return the mapped ReviewEntity
     */
    public ReviewModel mapToReviewModel(IncomingReviewEvent reviewEvent) {
        return new ReviewModel(
                reviewEvent.id(),
                reviewEvent.reviewedAccountId(),
                reviewEvent.reviewerAccountId(),
                reviewEvent.score(),
                reviewEvent.title(),
                reviewEvent.description(),
                reviewEvent.transformToLocalDateTime(reviewEvent.createdAt()),
                reviewEvent.transformToLocalDateTime(reviewEvent.updatedAt()),
                reviewEvent.transformToLocalDateTime(reviewEvent.deletedAt())
        );
    }
}
