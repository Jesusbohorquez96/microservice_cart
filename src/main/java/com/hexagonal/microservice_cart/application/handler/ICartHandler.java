package com.hexagonal.microservice_cart.application.handler;

import com.hexagonal.microservice_cart.application.dto.CartRequest;

public interface ICartHandler {

    void saveCartIn(CartRequest cartRequest);

    void createCart(CartRequest cartRequest);

    void addItemsToCart(CartRequest cartRequest);
}
 