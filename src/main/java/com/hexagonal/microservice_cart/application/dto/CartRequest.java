package com.hexagonal.microservice_cart.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CartRequest {

    private Long userId;
    private Long articleId;
    private int quantity;
}
