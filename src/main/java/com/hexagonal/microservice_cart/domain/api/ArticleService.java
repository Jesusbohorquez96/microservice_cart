package com.hexagonal.microservice_cart.domain.api;

import com.hexagonal.microservice_cart.application.dto.ArticleResponse;
import com.hexagonal.microservice_cart.infrastructure.adapters.feign_client.ArticleClient;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleClient articleClient;

    public ArticleService(ArticleClient articleClient) {
        this.articleClient = articleClient;
    }

    public ArticleResponse getArticleById(Long articleId) {
       return articleClient.getArticle(articleId);
    }
}