package com.yinnohs.reviwts.reviewProcesor.domain.ports.driven;

import com.yinnohs.reviwts.reviewProcesor.application.dto.IncomingReviewEvent;

public interface ReviewEventService {
    /**
     * Processes an incoming review event.
     *
     * @param reviewEvent the incoming review event to process
     */
    void processReviewEvent(IncomingReviewEvent reviewEvent);
}
