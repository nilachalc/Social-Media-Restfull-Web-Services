package com.prac.rest.webservice.RestfulservicesDemo;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfulServicesDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulServicesDemoApplication.class, args);
	}

	//The caller should pass the the local value every time for this type of implementation.
	//@Bean
	//public LocaleResolver localResolver() {
	//	SessionLocaleResolver resolver = new SessionLocaleResolver();
	//	resolver.setDefaultLocale(Locale.US);
	//	return resolver;
	//}
	
	// While using the AcceptHeaderLocaleResolver, the caller must have to send the "Accept-Language" header property with the required locale as the value.
	@Bean
	public LocaleResolver localResolver() {
		AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
		resolver.setDefaultLocale(Locale.US);
		return resolver;
	}		
	
	//This is Plain Spring application configuration. We can replace the below bean by adding 1 line in application.properties
	//file for Spring Boot Application.
	//@Bean
	//public ResourceBundleMessageSource messageSource() {
	//	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	//	messageSource.setBasename("message");
	//	return messageSource;
	//}
}
