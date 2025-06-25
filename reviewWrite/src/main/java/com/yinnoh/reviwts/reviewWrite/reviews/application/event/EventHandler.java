package com.yinnoh.reviwts.reviewWrite.reviews.application.event;

import com.yinnoh.reviwts.reviewWrite.reviews.domain.event.Event;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.ports.driver.EventSender;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EventHandler<T> {
    private final EventSender<T> eventSender;
    public void sendEvent(Event<T> event){
        eventSender.sendEvent(event);
    }
}
