package com.hexagonal.microservice_cart.infrastructure.adapters.feign_client;

import com.hexagonal.microservice_cart.application.dto.ArticleResponse;
import com.hexagonal.microservice_cart.infrastructure.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "emazon-hexagonal", url = "http://localhost:8080/articles", name = "emazon-hexagonal", configuration = FeignConfig.class)
public interface ArticleClient {

    @GetMapping(value = "/{articleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ArticleResponse getArticle(@PathVariable("articleId") Long articleId);

    @GetMapping(value = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE)
    Page<ArticleResponse> getArticlesByFilter(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String sortDirection,
            @RequestParam(required = false) List<Long> articleIds,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String brandName
    );
}
