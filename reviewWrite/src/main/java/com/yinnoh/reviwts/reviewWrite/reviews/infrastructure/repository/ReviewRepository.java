package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.repository;

import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.model.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewModel,String> {
}
