package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.repository;

import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.model.ReviewModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MongoReviewRepository extends MongoRepository<ReviewModel, String> {
    List<ReviewModel> findByReviewedAccountId(String reviewedAccountId);
    List<ReviewModel> findByReviewerAccountId(String reviewerAccountId);
    @Query("{'score': {$gte: ?0}}")
    List<ReviewModel> findByScoreGreaterThanEqual(double score);
}

