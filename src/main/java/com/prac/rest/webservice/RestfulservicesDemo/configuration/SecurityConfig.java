package com.prac.rest.webservice.restfulservicesdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class SecurityConfig{
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
		DefaultSecurityFilterChain builder = null;
		try {
			httpSecurity
            .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless services like microservices
            .authorizeHttpRequests(authz -> authz
                .anyRequest().authenticated()
            )
            .httpBasic(); // Needs to removed.
			builder = httpSecurity.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder;
	}
}