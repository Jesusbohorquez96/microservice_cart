package com.hexagonal.microservice_cart.domain.api;

import com.hexagonal.microservice_cart.domain.model.Cart;

public interface ICartServicePort {

    void saveCart(Cart cart);

    void createCart(Cart cart);

    void addItemsToCart(Cart cart);

    void removeItemFromCart(Long userId, Long articleId);
}
