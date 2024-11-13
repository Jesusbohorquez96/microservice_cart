package com.hexagonal.microservice_cart.domain.api;

import com.hexagonal.microservice_cart.domain.model.Cart;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.entity.CartEntity;
import org.springframework.data.domain.Page;

public interface ICartServicePort {

    void saveCart(Cart cart);

    void removeItemFromCart(Long userId, Long articleId);

     Page<CartEntity> getCartByUserId(int userId, int page, int size, String sortBy, boolean ascending);

}
