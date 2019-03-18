package com.example.microservicedemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DummyController {

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
