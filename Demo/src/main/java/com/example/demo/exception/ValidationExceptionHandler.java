package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ValidationExceptionHandler {

//TODO: SpringBoot: Practical 6 - Implementing custom error handling mechanisms.
// Create a new custom exception handler method below for NumberFormatException - Refer to ItemValidation.java
// Ensure error is log as below
// [demo] [nio-8080-exec-1] c.e.d.e.ValidationExceptionHandler       : NumberFormatException handled:
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, Object>> handleRuntimeValidationException(RuntimeException ex) {

		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("type", "custom exception");
		responseBody.put("status", HttpStatus.BAD_REQUEST.value());
		responseBody.put("errors", ex.getMessage());

		ResponseEntity<Map<String, Object>> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(responseBody);

		if (log.isErrorEnabled()) {
			log.error("handleRuntimeValidationException Exception handled: {}", responseEntity);
		}
		return responseEntity;

	}
}
