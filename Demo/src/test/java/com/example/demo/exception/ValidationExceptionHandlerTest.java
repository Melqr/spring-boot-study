package com.example.demo.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;


class ValidationExceptionHandlerTest {

    private ValidationExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new ValidationExceptionHandler();
    }

    @Test
    void testHandleRuntimeValidationException_ReturnsBadRequestResponse() {
        // Given
        RuntimeException exception = new RuntimeException("This is a runtime validation error");

        // When
        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleRuntimeValidationException(exception);

        assertNotNull(response.getBody().get("type"));
    }
}
