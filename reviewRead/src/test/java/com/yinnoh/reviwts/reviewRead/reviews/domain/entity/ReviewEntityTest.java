package com.yinnoh.reviwts.reviewRead.reviews.domain.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ReviewEntityTest {
    @Test
    void builderAndGetters() {
        LocalDateTime now = LocalDateTime.now();
        Review review = Review.builder()
                .id("1")
                .reviewedAccountId("acc1")
                .reviewerAccountId("acc2")
                .score(4.5)
                .title("Great!")
                .description("Very good.")
                .createdAt(now)
                .updatedAt(now)
                .deletedAt(null)
                .build();
        assertEquals("1", review.getId());
        assertEquals("acc1", review.getReviewedAccountId());
        assertEquals("acc2", review.getReviewerAccountId());
        assertEquals(4.5, review.getScore());
        assertEquals("Great!", review.getTitle());
        assertEquals("Very good.", review.getDescription());
        assertEquals(now, review.getCreatedAt());
        assertEquals(now, review.getUpdatedAt());
        assertNull(review.getDeletedAt());
    }
}

