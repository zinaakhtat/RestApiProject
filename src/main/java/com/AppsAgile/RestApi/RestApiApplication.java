package com.AppsAgile.RestApi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition
public class RestApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(RestApiApplication.class, args);

	}

}
