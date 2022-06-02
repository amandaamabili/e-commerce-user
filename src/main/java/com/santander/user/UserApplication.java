package com.santander.user;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Ecommerce API", version = "1.0", description = "Information about e-commerce user API"))
public class UserApplication {

	@Bean
	public RestTemplate buildRestTemplate() {
		return new RestTemplateBuilder().build();
	}
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
}




