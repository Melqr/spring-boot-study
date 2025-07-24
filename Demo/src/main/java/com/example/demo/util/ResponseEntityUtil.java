package com.example.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityUtil {

    // --- Utility: standard response creation ---
    public static ResponseEntity<String> buildResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }
}
