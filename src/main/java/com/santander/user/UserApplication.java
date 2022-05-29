package com.santander.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserApplication {

	@Bean
	public RestTemplate buildRestTemplate() {
		return new RestTemplateBuilder().build();
	}
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
}




