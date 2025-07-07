# ReviewRead Service: Comprehensive Test Plan

## 1. Domain Layer
### 1.1 Review Entity
- **Test:** Validate builder, getters, setters, and equals/hashCode.
- **How:**
  - Create Review objects with all fields.
  - Assert field values, equality, and immutability.
- **Example Test Class:**
  ```java
  class ReviewEntityTest {
      @Test void builderAndGetters() { /* ... */ }
      @Test void equalsAndHashCode() { /* ... */ }
  }
  ```

### 1.2 Repository & Service Interfaces
- **Test:** Contract tests using mocks or stubs.
- **How:**
  - Mock implementations to verify method signatures and expected exceptions.

## 2. Application Layer
### 2.1 Use Cases
- **Test:** Each use case (GetAllReviewsUseCase, GetReviewByIdUseCase, GetFilteredReviewsUseCase)
- **How:**
  - Mock ReviewService and ReviewMapper.
  - Test logic for filtering, mapping, and null/empty results.
- **Example Test Class:**
  ```java
  class GetAllReviewsUseCaseTest {
      @Test void returnsMappedResponses() { /* ... */ }
      @Test void returnsEmptyListIfNoReviews() { /* ... */ }
  }
  ```

### 2.2 DTOs
- **Test:** Record construction and field mapping.
- **How:**
  - Instantiate DTOs and assert field values.

## 3. Infrastructure Layer
### 3.1 ReviewMapper
- **Test:** All mapping methods (toDomain, toModel, toResponse, toDomainFromResponse, toGrpc)
- **How:**
  - Provide sample Review, ReviewModel, and ReviewResponse objects.
  - Assert all fields are mapped correctly, including null and edge cases.
- **Example Test Class:**
  ```java
  class ReviewMapperTest {
      @Test void mapsReviewModelToDomain() { /* ... */ }
      @Test void mapsDomainToModel() { /* ... */ }
      @Test void mapsDomainToResponse() { /* ... */ }
      @Test void mapsResponseToDomain() { /* ... */ }
      @Test void mapsToGrpc() { /* ... */ }
  }
  ```

### 3.2 Repository Implementation
- **Test:** ReviewRepositoryImpl logic
- **How:**
  - Mock MongoReviewRepository and ReviewMapper.
  - Test all find methods for correct mapping and delegation.

### 3.3 Controller
- **Test:** ReviewController endpoints
- **How:**
  - Use Spring MockMvc to simulate HTTP requests.
  - Test all endpoints for correct status, response body, and error handling.
- **Example Test Class:**
  ```java
  @WebMvcTest(ReviewController.class)
  class ReviewControllerTest {
      @Test void getAllReviewsReturnsOk() { /* ... */ }
      @Test void getReviewByIdReturnsNotFound() { /* ... */ }
      @Test void getFilteredReviewsReturnsFiltered() { /* ... */ }
  }
  ```

### 3.4 Exception Handling
- **Test:** Custom exception mappers and error responses.
- **How:**
  - Simulate error scenarios and assert correct HTTP status and error body.

## 4. Integration Tests
- **Test:** End-to-end flow from controller to MongoDB.
- **How:**
  - Use @SpringBootTest with embedded MongoDB or test containers.
  - Test real HTTP requests and assert database state.

## 5. Test Coverage Goals
- All mapping logic (ReviewMapper)
- All use case logic (filtering, mapping, null/empty handling)
- All controller endpoints (status, body, error)
- Repository delegation and mapping
- Entity and DTO correctness
- Exception handling

## 6. Example Directory Structure
```
src/test/java/com/yinnoh/reviwts/reviewRead/reviews/
  application/
    GetAllReviewsUseCaseTest.java
    GetReviewByIdUseCaseTest.java
    GetFilteredReviewsUseCaseTest.java
  domain/
    entity/ReviewEntityTest.java
  infrastructure/
    controller/ReviewControllerTest.java
    mapper/ReviewMapperTest.java
    repository/ReviewRepositoryImplTest.java
```

---

This plan ensures all critical logic, mapping, and endpoints are covered by unit and integration tests. Each test class should use mocks for dependencies except for integration tests, which should use real or embedded infrastructure.
