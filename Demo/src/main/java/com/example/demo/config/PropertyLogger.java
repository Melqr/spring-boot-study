package com.example.demo.config;

import java.util.Arrays;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PropertyLogger implements CommandLineRunner {

    // Constants for property filtering
    private static final String SPRING_PREFIX = "spring.";
    private static final String SENSITIVE_VALUE_MASK = "[SENSITIVE VALUE]";
    private static final Set<String> SENSITIVE_KEYWORDS = Set.of("password", "secret", "credential", "token", "key"); // Added more keywords

    private final ConfigurableEnvironment environment;

    public PropertyLogger(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("==================== Spring-related Application Properties ====================");

        // Iterate through all property sources
        for (PropertySource<?> ps : environment.getPropertySources()) {
            // Only process EnumerablePropertySource to get individual properties
            if (ps instanceof EnumerablePropertySource) {
                EnumerablePropertySource<?> eps = (EnumerablePropertySource<?>) ps;
                log.info("--- Source: {} ---", eps.getName()); // Using parameterized logging

                Arrays.stream(eps.getPropertyNames())
                      .sorted() // Sort for better readability
                      .filter(propName -> propName.startsWith(SPRING_PREFIX)) // Filter for "spring." prefix
                      .forEach(propName -> {
                          String propertyValue = environment.getProperty(propName);
                          // Check if the property name contains any sensitive keywords (case-insensitive)
                          boolean isSensitive = SENSITIVE_KEYWORDS.stream()
                                  .anyMatch(keyword -> propName.toLowerCase().contains(keyword));

                          if (isSensitive) {
                        	  log.info("{} = {}", propName, SENSITIVE_VALUE_MASK); // Parameterized logging for sensitive values
                          } else {
                        	  log.info("{} = {}", propName, propertyValue); // Parameterized logging
                          }
                      });
            }
        }
        log.info("===============================================================================");
    }
}