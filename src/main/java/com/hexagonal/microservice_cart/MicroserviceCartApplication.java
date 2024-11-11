package com.hexagonal.microservice_cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCartApplication.class, args);
    }
}