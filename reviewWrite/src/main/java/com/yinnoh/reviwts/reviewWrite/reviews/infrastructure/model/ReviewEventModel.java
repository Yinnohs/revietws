package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.model;

import com.yinnoh.reviwts.reviewWrite.reviews.domain.entity.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "review-event-store")
public class ReviewEventModel {
    @Id
    public String eventId;
    @Column(nullable = false)
    public String EventName;
    @Column(nullable = false)
    public Review data;
    @Column(nullable = false)
    public LocalDateTime createdAt;
}
