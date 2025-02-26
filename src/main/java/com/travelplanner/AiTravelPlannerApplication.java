package com.travelplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AiTravelPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiTravelPlannerApplication.class, args);
	}

	// Bean for making HTTP requests
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
