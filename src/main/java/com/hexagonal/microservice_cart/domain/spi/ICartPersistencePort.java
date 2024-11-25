package com.hexagonal.microservice_cart.domain.spi;

import com.hexagonal.microservice_cart.domain.model.Cart;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.entity.CartEntity;

import java.util.List;

public interface ICartPersistencePort {

    void saveCart(Cart cart);

    void createCart(Cart cart);

    void addItemsToCart(Cart cart);

    List<Cart> getCartItemsByUserId(Long userId);

    void removeItemFromCart(Cart cart);

    List<Cart> getCartByUserId(Long userId);

    CartEntity findProductByUserIdAndProductId(Long userId, Long articleId);

    void clearCartByUserId(Long userId);
}
