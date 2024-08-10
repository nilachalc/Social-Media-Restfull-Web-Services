package com.prac.rest.webservice.restfulservicesdemo.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
		// The below lines are commented because they can only be used once to hash the password and insert nto the DB
		// Otherwise it would start giving unique key violation error from the DB.
		
		//UserDetails user = User.withUsername("Olivia").password("Olivia@m").passwordEncoder(pass -> passwordEncoder().encode(pass)).roles("USER").build();
		//userDetailsManager.createUser(user);
		
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?");
        return userDetailsManager;
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
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