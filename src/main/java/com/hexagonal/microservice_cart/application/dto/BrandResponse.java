package com.hexagonal.microservice_cart.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BrandResponse {

    private Long brandId;
    private String brandName;
}
