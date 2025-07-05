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

