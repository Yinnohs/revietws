package com.yinnohs.reviwts.reviewProcesor.application.usecase;

import com.yinnohs.reviwts.reviewProcesor.application.dto.IncomingReviewEvent;
import com.yinnohs.reviwts.reviewProcesor.domain.ports.driven.ReviewEventService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProcessCreateReviewUseCase {

    private final ReviewEventService eventService;

    public void execute(IncomingReviewEvent reviewEvent) {
        eventService.processReviewEvent(reviewEvent);
    }
}
