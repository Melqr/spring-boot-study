package com.bank.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
	/**
	 * Configures a SecurityFilterChain that effectively disables all Spring
	 * Security protections by permitting all requests. This is typically used for
	 * development or testing purposes only and is NOT recommended for production
	 * environments.
	 *
	 * @param http The HttpSecurity object to configure.
	 * @return A configured SecurityFilterChain.
	 * @throws Exception if an error occurs during configuration.
	 */
	@Bean // Marks this method's return value as a Spring bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll() // Allow all requests to any endpoint
		).csrf(csrf -> csrf.disable()) // Disable CSRF protection (NOT recommended for production)
				.cors(cors -> cors.disable()) // Disable CORS checks (NOT recommended for production)
				.headers(headers -> headers.frameOptions(FrameOptionsConfig::disable)); // Allow iframes for
																									// H2 console etc.
																									// (NOT recommended
																									// for production)

		return http.build();
	}
}
