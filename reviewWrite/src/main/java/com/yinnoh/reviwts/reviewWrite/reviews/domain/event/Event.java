package com.yinnoh.reviwts.reviewWrite.reviews.domain.event;

import java.time.LocalDateTime;

public abstract class Event<T> {
    public String eventId;
    public String EventName;
    public T data;
    public LocalDateTime createdAt;

    public abstract void executeEvent();
}
