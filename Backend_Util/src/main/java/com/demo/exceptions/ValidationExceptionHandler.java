package com.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.utility_classes.ResponseEntityUtil;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ValidationExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		ResponseEntity<Map<String, Object>> responseEntity = ResponseEntityUtil
				.getResponseEntity(ValidationExceptionHandler.class.toString(), HttpStatus.INTERNAL_SERVER_ERROR, ex);

		if (log.isErrorEnabled()) {
			log.error("Exception handled: {}", responseEntity);
		}
		return responseEntity;

	}
}