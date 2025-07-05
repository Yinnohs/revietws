# ReviewRead Service Implementation Plan

## Overview
This document outlines the step-by-step implementation plan to transform the reviewRead service into a MongoDB-consuming application following the domain/application/infrastructure architecture pattern used in reviewWrite service.

## Current State Analysis
- **reviewRead** service currently has minimal structure with only the main application class
- **reviewWrite** service uses a clean architecture with domain/application/infrastructure layers
- Target: Connect reviewRead to MongoDB to retrieve review information

## Architecture Pattern (Based on reviewWrite)
```
reviews/
├── domain/
│   ├── entity/          # Domain entities
│   └── ports/           # Interfaces (repositories, services)
├── application/         # Use cases and DTOs
│   └── dto/            # Data Transfer Objects
└── infrastructure/     # External concerns (DB, REST, config)
    ├── configuration/  # Spring configuration
    ├── controller/     # REST controllers
    ├── mapper/         # Entity-Model mappers
    ├── model/          # MongoDB models
    └── repository/     # MongoDB repositories
```

## Implementation Steps

### Step 1: Update Build Dependencies
**File**: `build.gradle`

Add the following dependencies:
```gradle
dependencies {
    // MongoDB dependencies
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
    
    // Lombok for reducing boilerplate
    implementation 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    
    // Validation
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    
    // Web for REST endpoints
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

### Step 2: Configure MongoDB Connection
**File**: `src/main/resources/application.yml` (rename from .properties)

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://yinnohs:123@localhost:27017/review-read?authSource=admin&authMechanism=SCRAM-SHA-1
  application:
    name: review-read

server:
  port: 8082
```

### Step 3: Create Domain Layer

#### 3.1 Create Domain Entity
**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/domain/entity/Review.java`

```java
package com.yinnoh.reviwts.reviewRead.reviews.domain.entity;

import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Review {
    private String id;
    private String reviewedAccountId;
    private String reviewerAccountId;
    private double score;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
```

#### 3.2 Create Domain Repository Port
**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/domain/ports/ReviewRepository.java`

```java
package com.yinnoh.reviwts.reviewRead.reviews.domain.ports;

import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    List<Review> findAll();
    Optional<Review> findById(String id);
    List<Review> findByReviewedAccountId(String reviewedAccountId);
    List<Review> findByReviewerAccountId(String reviewerAccountId);
    List<Review> findByScoreGreaterThan(double score);
}
```

#### 3.3 Create Domain Service Port
**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/domain/ports/ReviewService.java`

```java
package com.yinnoh.reviwts.reviewRead.reviews.domain.ports;

import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> getAllReviews();
    Optional<Review> getReviewById(String id);
    List<Review> getReviewsByReviewedAccount(String reviewedAccountId);
    List<Review> getReviewsByReviewer(String reviewerAccountId);
    List<Review> getReviewsWithMinimumScore(double minimumScore);
}
```

### Step 4: Create Application Layer

#### 4.1 Create DTOs
**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/application/dto/ReviewResponse.java`

```java
package com.yinnoh.reviwts.reviewRead.reviews.application.dto;

import java.time.LocalDateTime;

public record ReviewResponse(
    String id,
    String reviewedAccountId,
    String reviewerAccountId,
    double score,
    String title,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) {}
```

**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/application/dto/ReviewFilterRequest.java`

```java
package com.yinnoh.reviwts.reviewRead.reviews.application.dto;

public record ReviewFilterRequest(
    String reviewedAccountId,
    String reviewerAccountId,
    Double minimumScore
) {}
```

#### 4.2 Create Use Cases
**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/application/GetAllReviewsUseCase.java`

```java
package com.yinnoh.reviwts.reviewRead.reviews.application;

import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class GetAllReviewsUseCase {
    private final ReviewService reviewService;

    public List<ReviewResponse> execute() {
        return reviewService.getAllReviews()
            .stream()
            .map(review -> new ReviewResponse(
                review.getId(),
                review.getReviewedAccountId(),
                review.getReviewerAccountId(),
                review.getScore(),
                review.getTitle(),
                review.getDescription(),
                review.getCreatedAt(),
                review.getUpdatedAt(),
                review.getDeletedAt()
            ))
            .toList();
    }
}
```

**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/application/GetReviewByIdUseCase.java`

```java
package com.yinnoh.reviwts.reviewRead.reviews.application;

import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class GetReviewByIdUseCase {
    private final ReviewService reviewService;

    public Optional<ReviewResponse> execute(String id) {
        return reviewService.getReviewById(id)
            .map(review -> new ReviewResponse(
                review.getId(),
                review.getReviewedAccountId(),
                review.getReviewerAccountId(),
                review.getScore(),
                review.getTitle(),
                review.getDescription(),
                review.getCreatedAt(),
                review.getUpdatedAt(),
                review.getDeletedAt()
            ));
    }
}
```

**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/application/GetFilteredReviewsUseCase.java`

```java
package com.yinnoh.reviwts.reviewRead.reviews.application;

import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewFilterRequest;
import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class GetFilteredReviewsUseCase {
    private final ReviewService reviewService;

    public List<ReviewResponse> execute(ReviewFilterRequest filter) {
        Stream<Review> reviewStream = reviewService.getAllReviews().stream();

        if (filter.reviewedAccountId() != null) {
            reviewStream = reviewService.getReviewsByReviewedAccount(filter.reviewedAccountId()).stream();
        } else if (filter.reviewerAccountId() != null) {
            reviewStream = reviewService.getReviewsByReviewer(filter.reviewerAccountId()).stream();
        }

        if (filter.minimumScore() != null) {
            reviewStream = reviewStream.filter(review -> review.getScore() >= filter.minimumScore());
        }

        return reviewStream
            .map(review -> new ReviewResponse(
                review.getId(),
                review.getReviewedAccountId(),
                review.getReviewerAccountId(),
                review.getScore(),
                review.getTitle(),
                review.getDescription(),
                review.getCreatedAt(),
                review.getUpdatedAt(),
                review.getDeletedAt()
            ))
            .toList();
    }
}
```

### Step 5: Create Infrastructure Layer

#### 5.1 Create MongoDB Model
**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/infrastructure/model/ReviewModel.java`

```java
package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "reviews")
public class ReviewModel {
    @Id
    private String id;
    @NonNull
    @Field(targetType = org.bson.BsonType.STRING)
    private String reviewedAccountId;
    @NonNull
    @Field(targetType = org.bson.BsonType.STRING)
    private String reviewerAccountId;
    @NonNull
    private double score;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private LocalDateTime createdAt;
    @NonNull
    private LocalDateTime updatedAt;
    @NonNull
    private LocalDateTime deletedAt;
}
```

#### 5.2 Create MongoDB Repository
**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/infrastructure/repository/MongoReviewRepository.java`

```java
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
```

#### 5.3 Create Entity-Model Mapper
**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/infrastructure/mapper/ReviewMapper.java`

```java
package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.mapper;

import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.model.ReviewModel;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    
    public Review toDomain(ReviewModel model) {
        if (model == null) return null;
        
        return Review.builder()
            .id(model.getId())
            .reviewedAccountId(model.getReviewedAccountId())
            .reviewerAccountId(model.getReviewerAccountId())
            .score(model.getScore())
            .title(model.getTitle())
            .description(model.getDescription())
            .createdAt(model.getCreatedAt())
            .updatedAt(model.getUpdatedAt())
            .deletedAt(model.getDeletedAt())
            .build();
    }
}
```

#### 5.4 Create Repository Implementation
**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/infrastructure/repository/ReviewRepositoryImpl.java`

```java
package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.repository;

import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewRepository;
import com.yinnoh.reviwts.reviewRead.reviews.infrastructure.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
    private final MongoReviewRepository mongoRepository;
    private final ReviewMapper mapper;

    @Override
    public List<Review> findAll() {
        return mongoRepository.findAll()
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public Optional<Review> findById(String id) {
        return mongoRepository.findById(id)
            .map(mapper::toDomain);
    }

    @Override
    public List<Review> findByReviewedAccountId(String reviewedAccountId) {
        return mongoRepository.findByReviewedAccountId(reviewedAccountId)
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public List<Review> findByReviewerAccountId(String reviewerAccountId) {
        return mongoRepository.findByReviewerAccountId(reviewerAccountId)
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public List<Review> findByScoreGreaterThan(double score) {
        return mongoRepository.findByScoreGreaterThanEqual(score)
            .stream()
            .map(mapper::toDomain)
            .toList();
    }
}
```

#### 5.5 Create Service Implementation
**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/infrastructure/service/ReviewServiceImpl.java`

```java
package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.service;

import com.yinnoh.reviwts.reviewRead.reviews.domain.entity.Review;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewRepository;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> getReviewById(String id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> getReviewsByReviewedAccount(String reviewedAccountId) {
        return reviewRepository.findByReviewedAccountId(reviewedAccountId);
    }

    @Override
    public List<Review> getReviewsByReviewer(String reviewerAccountId) {
        return reviewRepository.findByReviewerAccountId(reviewerAccountId);
    }

    @Override
    public List<Review> getReviewsWithMinimumScore(double minimumScore) {
        return reviewRepository.findByScoreGreaterThan(minimumScore);
    }
}
```

#### 5.6 Create REST Controller
**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/infrastructure/controller/ReviewController.java`

```java
package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.controller;

import com.yinnoh.reviwts.reviewRead.reviews.application.GetAllReviewsUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.application.GetFilteredReviewsUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.application.GetReviewByIdUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewFilterRequest;
import com.yinnoh.reviwts.reviewRead.reviews.application.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final GetAllReviewsUseCase getAllReviewsUseCase;
    private final GetReviewByIdUseCase getReviewByIdUseCase;
    private final GetFilteredReviewsUseCase getFilteredReviewsUseCase;

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAllReviews() {
        List<ReviewResponse> reviews = getAllReviewsUseCase.execute();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable String id) {
        return getReviewByIdUseCase.execute(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ReviewResponse>> getFilteredReviews(
            @RequestParam(required = false) String reviewedAccountId,
            @RequestParam(required = false) String reviewerAccountId,
            @RequestParam(required = false) Double minimumScore) {
        
        ReviewFilterRequest filter = new ReviewFilterRequest(
            reviewedAccountId, reviewerAccountId, minimumScore);
        
        List<ReviewResponse> reviews = getFilteredReviewsUseCase.execute(filter);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByAccount(@PathVariable String accountId) {
        ReviewFilterRequest filter = new ReviewFilterRequest(accountId, null, null);
        List<ReviewResponse> reviews = getFilteredReviewsUseCase.execute(filter);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/reviewer/{reviewerId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByReviewer(@PathVariable String reviewerId) {
        ReviewFilterRequest filter = new ReviewFilterRequest(null, reviewerId, null);
        List<ReviewResponse> reviews = getFilteredReviewsUseCase.execute(filter);
        return ResponseEntity.ok(reviews);
    }
}
```

#### 5.7 Create Configuration
**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/infrastructure/configuration/ReviewConfiguration.java`

```java
package com.yinnoh.reviwts.reviewRead.reviews.infrastructure.configuration;

import com.yinnoh.reviwts.reviewRead.reviews.application.GetAllReviewsUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.application.GetFilteredReviewsUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.application.GetReviewByIdUseCase;
import com.yinnoh.reviwts.reviewRead.reviews.domain.ports.ReviewService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewConfiguration {

    @Bean
    public GetAllReviewsUseCase getAllReviewsUseCase(ReviewService reviewService) {
        return new GetAllReviewsUseCase(reviewService);
    }

    @Bean
    public GetReviewByIdUseCase getReviewByIdUseCase(ReviewService reviewService) {
        return new GetReviewByIdUseCase(reviewService);
    }

    @Bean
    public GetFilteredReviewsUseCase getFilteredReviewsUseCase(ReviewService reviewService) {
        return new GetFilteredReviewsUseCase(reviewService);
    }
}
```

### Step 6: Update Main Application Class
**File**: `src/main/java/com/yinnoh/reviwts/reviewRead/ReviewReadApplication.java`

```java
package com.yinnoh.reviwts.reviewRead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ReviewReadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReviewReadApplication.class, args);
    }
}
```

## Final Folder Structure
After implementation, the reviewRead service will have the following structure:

```
src/main/java/com/yinnoh/reviwts/reviewRead/
├── ReviewReadApplication.java
└── reviews/
    ├── domain/
    │   ├── entity/
    │   │   └── Review.java
    │   └── ports/
    │       ├── ReviewRepository.java
    │       └── ReviewService.java
    ├── application/
    │   ├── dto/
    │   │   ├── ReviewResponse.java
    │   │   └── ReviewFilterRequest.java
    │   ├── GetAllReviewsUseCase.java
    │   ├── GetReviewByIdUseCase.java
    │   └── GetFilteredReviewsUseCase.java
    └── infrastructure/
        ├── configuration/
        │   └── ReviewConfiguration.java
        ├── controller/
        │   └── ReviewController.java
        ├── mapper/
        │   └── ReviewMapper.java
        ├── model/
        │   └── ReviewModel.java
        ├── repository/
        │   ├── MongoReviewRepository.java
        │   └── ReviewRepositoryImpl.java
        └── service/
            └── ReviewServiceImpl.java
```

## API Endpoints
After implementation, the following endpoints will be available:

- `GET /api/v1/reviews` - Get all reviews
- `GET /api/v1/reviews/{id}` - Get review by ID
- `GET /api/v1/reviews/filter?reviewedAccountId=X&reviewerAccountId=Y&minimumScore=Z` - Get filtered reviews
- `GET /api/v1/reviews/account/{accountId}` - Get reviews for a specific account
- `GET /api/v1/reviews/reviewer/{reviewerId}` - Get reviews by a specific reviewer

## Implementation Order
1. Update build.gradle with dependencies
2. Configure MongoDB connection (application.yml)
3. Create domain layer (entities and ports)
4. Create application layer (use cases and DTOs)
5. Create infrastructure layer (models, repositories, services, controllers)
6. Update main application class
7. Test the implementation

## Notes
- Ensure MongoDB is running and accessible with the provided credentials
- The collection name is "reviews" to match the existing data structure
- All fields are marked as @NonNull to enforce data integrity
- The implementation follows the same architectural pattern as reviewWrite service
- No modifications are made to the reviewWrite service as requested
