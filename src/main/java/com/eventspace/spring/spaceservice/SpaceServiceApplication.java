package com.eventspace.spring.spaceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpaceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpaceServiceApplication.class, args);
    }
}
