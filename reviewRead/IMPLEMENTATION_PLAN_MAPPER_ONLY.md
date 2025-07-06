# ReviewRead Service: Focused Mapper Implementation Plan

## Objective
Implement a single mapper in the infrastructure layer that maps between:
- The Review domain entity
- The ReviewModel (MongoDB database entity)
- The ReviewResponse DTO (API response)

## Steps

### 1. Implement the Mapper in the Infrastructure Layer
- **File:** `src/main/java/com/yinnoh/reviwts/reviewRead/reviews/infrastructure/mapper/ReviewMapper.java`
- The mapper should provide methods:
  - `Review toDomain(ReviewModel model)`
  - `ReviewModel toModel(Review entity)`
  - `ReviewResponse toResponse(Review entity)`
  - (Optionally) `Review toDomainFromResponse(ReviewResponse dto)`

### 2. Identify and Refactor All Usages

#### Classes to Change and How:

- **infrastructure/mapper/ReviewMapper.java**
  - Expand to include all mapping methods between Review, ReviewModel, and ReviewResponse.

- **infrastructure/repository/ReviewRepositoryImpl.java**
  - Use the mapper for all conversions between ReviewModel and Review in repository methods (e.g., findAll, findById, findByReviewedAccountId, findByReviewerAccountId, findByScoreGreaterThan).
  - If write operations are added, use the mapper for Review to ReviewModel as well.

- **application/GetAllReviewsUseCase.java**
  - Use the mapper to convert from Review to ReviewResponse in the execute() method.

- **application/GetReviewByIdUseCase.java**
  - Use the mapper to convert from Review to ReviewResponse in the execute() method.

- **application/GetFilteredReviewsUseCase.java**
  - Use the mapper to convert from Review to ReviewResponse in the execute() method.

- **infrastructure/controller/ReviewController.java**
  - Ensure all API responses use ReviewResponse created via the mapper, not by manual mapping.

### 3. Remove Redundant Mapping Code
- Delete or refactor any manual or duplicate mapping logic in repositories, use cases, or controllers to use the new mapper.

### 4. Register the Mapper as a Spring Bean
- Annotate the mapper with `@Component` so it can be injected where needed.

## Summary Table
| File/Class                                      | Change Description                                                                 |
|-------------------------------------------------|------------------------------------------------------------------------------------|
| infrastructure/mapper/ReviewMapper.java         | Add all mapping methods between entity, model, and DTO                             |
| infrastructure/repository/ReviewRepositoryImpl.java | Use mapper for all entity-model conversions                                        |
| application/GetAllReviewsUseCase.java           | Use mapper for Review → ReviewResponse mapping                                     |
| application/GetReviewByIdUseCase.java           | Use mapper for Review → ReviewResponse mapping                                     |
| application/GetFilteredReviewsUseCase.java      | Use mapper for Review → ReviewResponse mapping                                     |
| infrastructure/controller/ReviewController.java | Ensure all responses use ReviewResponse via the mapper                             |

## Current ReviewRead Service Structure

```
reviewRead/
  build.gradle
  ...
  src/
    main/
      java/
        com/
          yinnoh/
            reviwts/
              reviewRead/
                ReviewReadApplication.java
                reviews/
                  application/
                    GetAllReviewsUseCase.java
                    GetFilteredReviewsUseCase.java
                    GetReviewByIdUseCase.java
                    dto/
                      ReviewFilterRequest.java
                      ReviewResponse.java
                  domain/
                    entity/
                      Review.java
                    ports/
                      ReviewRepository.java
                      ReviewService.java
                  infrastructure/
                    configuration/
                      ReviewConfiguration.java
                    controller/
                      ReviewController.java
                    mapper/
                      ReviewMapper.java
                    model/
                      ReviewModel.java
                    repository/
                      MongoReviewRepository.java
                      ReviewRepositoryImpl.java
                    service/
                      ConcreteReviewService.java
      resources/
        application.yml
```

## Implementation Notes
- Only one mapper class in the infrastructure layer.
- The mapper handles all conversions between domain entity, database entity, and response DTO.
- All repository, use case, and controller logic should use this mapper for conversions.
- No mapper interface in the domain layer; the mapper is an infrastructure concern.

**File name:** IMPLEMENTATION_PLAN_MAPPER_ONLY.md
