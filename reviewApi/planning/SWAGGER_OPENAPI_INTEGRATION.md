# How to Add Swagger (OpenAPI) Documentation to reviewApi Spring Boot Project

This guide provides step-by-step instructions to integrate Swagger UI and OpenAPI specification generation into your Spring Boot project (Spring Boot 3.5.3, Java 21).

---

## 1. Add Swagger/OpenAPI Dependencies

**Edit `build.gradle`:**

Add the following dependency to the `dependencies` block:

```groovy
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0'
```

- This version is compatible with Spring Boot 3.x and Java 21.
- No need for older springfox dependencies.

---

## 2. (Optional) Customize OpenAPI Info

Create a configuration class (e.g., `OpenApiConfig.java`) in your main source folder:

```java
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Review API Service")
                .version("1.0")
                .description("API documentation for the Review API service."));
    }
}
```

---

## 3. Accessing Swagger UI

- After starting your Spring Boot application, navigate to:
  - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) (or your configured port)
- The OpenAPI JSON spec will be available at:
  - [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 4. (Optional) Advanced Configuration
- You can use annotations like `@Operation`, `@Parameter`, and `@Schema` on your controllers and DTOs for fine-grained documentation.
- For security, add `springdoc.swagger-ui.enabled=false` in `application.yml` for production.

---

## 5. Example Controller Annotation

```java
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Operation(summary = "Get all reviews")
    @GetMapping
    public List<ReviewResponse> getAllReviews() { /* ... */ }
}
```

---

## 6. Summary Checklist
- [ ] Add `springdoc-openapi-starter-webmvc-ui` dependency
- [ ] (Optional) Add `OpenApiConfig` for custom info
- [ ] Annotate controllers for better docs
- [ ] Access Swagger UI at `/swagger-ui.html`
- [ ] (Optional) Secure or disable in production

---

**References:**
- [Springdoc OpenAPI Documentation](https://springdoc.org/)
- [OpenAPI Specification](https://swagger.io/specification/)

