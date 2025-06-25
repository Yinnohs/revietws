package com.yinnoh.reviwts.reviewWrite.reviews.domain.ports.driver;

import com.yinnoh.reviwts.reviewWrite.reviews.domain.event.Event;

public interface EventSender<T> {
    public void sendEvent(Event<T> event);
}
