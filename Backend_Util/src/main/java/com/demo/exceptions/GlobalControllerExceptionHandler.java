package com.demo.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.utility_classes.ResponseEntityUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {
	
	@ExceptionHandler(DemoAppException.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {

        ResponseEntity<Map<String, Object>> responseEntity = ResponseEntityUtil.getResponseEntity("Demo App general Exception. ", HttpStatus.INTERNAL_SERVER_ERROR, ex);
        
        if (log.isErrorEnabled()) {
            log.error("Exception handled: {}", responseEntity);
        }
        
        return responseEntity;
    }
}
