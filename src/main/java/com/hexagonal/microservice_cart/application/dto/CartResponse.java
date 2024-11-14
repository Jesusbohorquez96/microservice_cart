package com.hexagonal.microservice_cart.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class CartResponse {

    private Long id;
    private Long userId;
    private Long articleId;
    private int quantity;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    private String articleName;
    private String articleDescription;
    private Integer articleStock;
    private Double articlePrice;
    private List<CategoryResponse> articleCategories;
    private BrandResponse articleBrand;
}
