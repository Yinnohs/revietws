package com.yinnohs.reviwts.reviewProcesor.application.dto;

import java.time.LocalDateTime;

public record IncomingReviewEvent(
        String id,
        double score,
        String title,
        int[] createdAt,
        int[] updatedAt,
        int[] deletedAt,
        String description,
        String reviewedAccountId,
        String reviewerAccountId
) {
    public LocalDateTime transformToLocalDateTime(int[] dateTime) {
        if (dateTime == null || dateTime.length < 6) {
            return null;
        }
        return LocalDateTime.of(dateTime[0], dateTime[1], dateTime[2], dateTime[3], dateTime[4], dateTime[5]);
    }
}
