package com.example.microservicedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
public class MicroserviceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceDemoApplication.class, args);
    }

}
