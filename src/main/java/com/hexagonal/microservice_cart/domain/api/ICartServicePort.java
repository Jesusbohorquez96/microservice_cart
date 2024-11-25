package com.hexagonal.microservice_cart.domain.api;

import com.hexagonal.microservice_cart.domain.model.Cart;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.entity.CartEntity;

import java.util.List;

public interface ICartServicePort {

    void saveCart(Cart cart);

    void removeItemFromCart(Long userId, Long articleId);

    List<CartEntity> getCartByUserId(Long userId);

    List<Long> getArticleIds(Long userId);

    CartEntity findProductByUserIdAndProductId(Long userId, Long articleId);

    void clearCartByUserId(Long userId);
}
