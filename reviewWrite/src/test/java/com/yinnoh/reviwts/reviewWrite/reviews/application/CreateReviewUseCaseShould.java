package com.yinnoh.reviwts.reviewWrite.reviews.application;

import com.yinnoh.reviwts.reviewWrite.reviews.application.dto.CreateReviewRequest;
import com.yinnoh.reviwts.reviewWrite.reviews.domain.ports.driver.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Executable;
import java.util.UUID;
import java.util.function.Function;

public class CreateReviewUseCaseShould {

    @Test
    public void generate_review(){
        // given
        CreateReviewRequest request = new CreateReviewRequest(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                2.5,
                "test title",
                "test description hello"
        );
        ReviewService service = Mockito.mock(ReviewService.class);
        CreateReviewUseCase useCase = new CreateReviewUseCase(service);

        // when / then
        Assertions.assertDoesNotThrow(()-> {useCase.execute(request);});
    }
}
