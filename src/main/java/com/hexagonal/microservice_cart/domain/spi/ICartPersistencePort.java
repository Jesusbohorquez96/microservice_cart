package com.hexagonal.microservice_cart.domain.spi;

import com.hexagonal.microservice_cart.domain.model.Cart;

import java.util.List;

public interface ICartPersistencePort {

    void saveCart(Cart cart);

    void createCart(Cart cart);

    void addItemsToCart(Cart cart);

    List<Cart> getCartItemsByUserId(Long userId);

    void removeItemFromCart(Cart cart);
}
