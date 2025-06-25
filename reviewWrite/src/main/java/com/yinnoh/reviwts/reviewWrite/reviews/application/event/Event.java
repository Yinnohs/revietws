package com.yinnoh.reviwts.reviewWrite.reviews.application.event;

import java.time.LocalDateTime;

public abstract class Event<T> {
    String eventId;
    String EventName;
    T data;
    LocalDateTime createdAt;

    public abstract void executeEvent();
}
