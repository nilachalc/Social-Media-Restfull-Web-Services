package com.prac.rest.webservice.restfulservicesdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	public static final Contact DEFAULT_CONTACT = new Contact();
	
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Restful-Services-Demo")
                        .version("1.0")
                        .description("Restful-Services-Demo is a demo for Reatfull Services using springdoc-openapi and OpenAPI 3.")
                        .contact(DEFAULT_CONTACT
                                .name("Nilachal Chakraborty")
                                .url("http://localhost:8081")
                                .email("cnilachal.19@gmail.com"))
                );
    }
}
