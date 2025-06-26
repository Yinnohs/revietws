package com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.repository;

import com.yinnoh.reviwts.reviewWrite.reviews.infrastructure.model.ReviewEventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewEventRepository extends JpaRepository<ReviewEventModel,String> {
}
