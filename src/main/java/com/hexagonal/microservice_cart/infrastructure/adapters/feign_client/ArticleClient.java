package com.hexagonal.microservice_cart.infrastructure.adapters.feign_client;

import com.hexagonal.microservice_cart.application.dto.ArticleResponse;
import com.hexagonal.microservice_cart.infrastructure.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "emazon-hexagonal", url = "http://localhost:8080/articles", name = "emazon-hexagonal", configuration = FeignConfig.class)
public interface ArticleClient {

    @GetMapping(value = "/{articleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ArticleResponse getArticle(@PathVariable("articleId") Long articleId);
}
