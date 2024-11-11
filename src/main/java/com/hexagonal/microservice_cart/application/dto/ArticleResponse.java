package com.hexagonal.microservice_cart.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@Getter
@Setter
public class ArticleResponse {

    private Long articleId;
    private String articleName;
    private String articleDescription;
    private Integer articleStock;
    private Double articlePrice;
    private List<CategoryResponse> articleCategories;
    private BrandResponse articleBrand;


    public String getCategory() {
        return "";
    }
}
