package com.hexagonal.microservice_cart.application.handler;

import com.hexagonal.microservice_cart.application.dto.ArticleResponse;
import com.hexagonal.microservice_cart.application.dto.CartRequest;
import com.hexagonal.microservice_cart.application.dto.CartResponse;
import com.hexagonal.microservice_cart.application.mapper.CartRequestMapper;
import com.hexagonal.microservice_cart.application.mapper.CartResponseMapper;
import com.hexagonal.microservice_cart.domain.api.ICartServicePort;
import com.hexagonal.microservice_cart.domain.model.Cart;
import com.hexagonal.microservice_cart.infrastructure.adapters.feign_client.ArticleClient;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.entity.CartEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartHandler implements ICartHandler {

    private final CartRequestMapper cartRequestMapper;
    private final ICartServicePort cartServicePort;
    private final CartResponseMapper cartResponseMapper;
    private final ArticleClient articleClient;

    @Override
    public void saveCartIn(CartRequest cartRequest) {
        Cart cart = cartRequestMapper.toCart(cartRequest);
        cartServicePort.saveCart(cart);
    }

    @Override
    public Page<CartResponse> getCartByUserId(Long userId, int page, int size, String sortBy, String sortDirection) {
        Page<CartEntity> cartEntities = cartServicePort.getCartByUserId(userId, page, size, sortBy, Boolean.parseBoolean(sortDirection));
        return cartEntities.map(cartEntity -> {
            CartResponse cartResponse = cartResponseMapper.toCartResponse(cartEntity);

            ArticleResponse article = articleClient.getArticle(cartEntity.getArticleId());

            cartResponse.setArticleName(article.getArticleName());
            cartResponse.setArticleDescription(article.getArticleDescription());
            cartResponse.setArticleStock(article.getArticleStock());
            cartResponse.setArticlePrice(article.getArticlePrice());
            cartResponse.setArticleCategories(article.getArticleCategories());
            cartResponse.setArticleBrand(article.getArticleBrand());

            return cartResponse;
        });
    }

    @Override
    public Page<CartResponse> getFilteredArticles(int page, int size, String sortBy, String sortDirection, String categoryName, String brandName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.valueOf(authentication.getName());
        List<Long> articleIds = cartServicePort.getArticleIds(userId);
        Page<ArticleResponse> articles = articleClient.getArticlesByFilter(page, size, sortBy, sortDirection, articleIds, categoryName, brandName);
        Page<CartResponse> cartResponses = articles.map(article -> {
            CartResponse cartResponse = new CartResponse();
            cartResponse.setArticleId(article.getArticleId());
            cartResponse.setArticleName(article.getArticleName());
            cartResponse.setArticleDescription(article.getArticleDescription());
            cartResponse.setArticleStock(article.getArticleStock());
            cartResponse.setArticlePrice(article.getArticlePrice());
            cartResponse.setArticleCategories(article.getArticleCategories());
            cartResponse.setArticleBrand(article.getArticleBrand());

            CartEntity cartEntity = cartServicePort.findProductByUserIdAndProductId(userId, article.getArticleId());
            if (cartEntity != null) {
                cartResponse.setQuantity(cartEntity.getQuantity());
                cartResponse.setUpdateDate(cartEntity.getUpdateDate());
            } else {
                cartResponse.setQuantity(0);
                cartResponse.setUpdateDate(null);
            }
            return cartResponse;

        });
        return cartResponses;
    }

    public void removeItemFromCart(Long userId, Long articleId) {
        cartServicePort.removeItemFromCart(userId, articleId);
    }
}