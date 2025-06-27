package com.yinnoh.reviwts.reviewWrite.reviews.domain.event;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public abstract class Event<T> {
    private String eventId;
    private String EventName;
    private T data;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean processed;
}
