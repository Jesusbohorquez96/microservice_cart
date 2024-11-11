package com.hexagonal.microservice_cart.domain.usecase;

import com.hexagonal.microservice_cart.domain.api.ICartServicePort;
import com.hexagonal.microservice_cart.domain.model.Cart;
import com.hexagonal.microservice_cart.domain.spi.ICartPersistencePort;

public class CartUseCase implements ICartServicePort {

    private final ICartPersistencePort cartPersistencePort;

    public CartUseCase(ICartPersistencePort cartPersistencePort) {
        this.cartPersistencePort = cartPersistencePort;
    }

    @Override
    public void saveCart(Cart cart) {
        cartPersistencePort.saveCart(cart);
    }

    @Override
    public void createCart(Cart cart) {
        cartPersistencePort.createCart(cart);
    }

    @Override
    public void addItemsToCart(Cart cart) {
        cartPersistencePort.addItemsToCart(cart);
    }
}