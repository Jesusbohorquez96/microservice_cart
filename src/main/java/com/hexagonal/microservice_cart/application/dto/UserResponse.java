package com.hexagonal.microservice_cart.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserResponse {

    private String id;
    private String name;
    private String email;
}
