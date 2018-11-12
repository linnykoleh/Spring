package com.learning.linnyk.restfulwebservices.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Swagger links:
	 * http://localhost:8080/swagger-ui.html#/
	 * http://localhost:8080/v2/api-docs
	 */

	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
			"Spring Boot API", "Spring Boot API Documentation", "1.0", "",
			new Contact("Oleh Linnyk", "", ""),
			"", "", Collections.emptyList());

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>() {{
		add("application/json");
		add("application/xml");
	}};

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}
}
