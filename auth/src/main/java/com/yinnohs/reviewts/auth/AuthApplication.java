package com.yinnohs.reviewts.auth;

import com.yinnohs.reviewts.auth.auth.infrastructure.configs.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class AuthApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(AuthApplication.class, args);
	}
}
