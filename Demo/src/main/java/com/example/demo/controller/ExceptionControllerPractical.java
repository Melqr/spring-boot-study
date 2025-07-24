package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Item;

@RestController
@RequestMapping("/demo/exception/v1") // Base path for all endpoints in this controller
public class ExceptionControllerPractical {

    @GetMapping
    public ResponseEntity<List<Item>> testException() throws RuntimeException {
        throw new RuntimeException("testException caught");
    }
}
