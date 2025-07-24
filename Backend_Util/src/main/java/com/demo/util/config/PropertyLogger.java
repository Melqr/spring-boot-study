package com.demo.util.config;

import java.util.Arrays;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class PropertyLogger implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(PropertyLogger.class);

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
        logger.info("==================== Spring-related Application Properties ====================");

        // Iterate through all property sources
        for (PropertySource<?> ps : environment.getPropertySources()) {
            // Only process EnumerablePropertySource to get individual properties
            if (ps instanceof EnumerablePropertySource) {
                EnumerablePropertySource<?> eps = (EnumerablePropertySource<?>) ps;
                logger.info("--- Source: {} ---", eps.getName()); // Using parameterized logging

                Arrays.stream(eps.getPropertyNames())
                      .sorted() // Sort for better readability
                      .filter(propName -> propName.startsWith(SPRING_PREFIX)) // Filter for "spring." prefix
                      .forEach(propName -> {
                          String propertyValue = environment.getProperty(propName);
                          // Check if the property name contains any sensitive keywords (case-insensitive)
                          boolean isSensitive = SENSITIVE_KEYWORDS.stream()
                                  .anyMatch(keyword -> propName.toLowerCase().contains(keyword));

                          if (isSensitive) {
                              logger.info("{} = {}", propName, SENSITIVE_VALUE_MASK); // Parameterized logging for sensitive values
                          } else {
                              logger.info("{} = {}", propName, propertyValue); // Parameterized logging
                          }
                      });
            }
        }
        logger.info("===============================================================================");
    }
}