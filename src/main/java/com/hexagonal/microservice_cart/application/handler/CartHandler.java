package com.hexagonal.microservice_cart.application.handler;

import com.hexagonal.microservice_cart.application.dto.CartRequest;
import com.hexagonal.microservice_cart.application.mapper.CartRequestMapper;
import com.hexagonal.microservice_cart.domain.api.ICartServicePort;
import com.hexagonal.microservice_cart.domain.model.Cart;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CartHandler implements ICartHandler {

    private final CartRequestMapper cartRequestMapper;
    private final ICartServicePort cartServicePort;

    @Override
    public void saveCartIn(CartRequest cartRequest) {
        Cart cart = cartRequestMapper.toCart(cartRequest);
        cartServicePort.saveCart(cart);
    }

    @Override
    public void createCart(CartRequest cartRequest) {
        Cart cart = cartRequestMapper.toCart(cartRequest);
        cartServicePort.createCart(cart);
    }

    @Override
    public void addItemsToCart(CartRequest cartRequest) {
        Cart cart = cartRequestMapper.toCart(cartRequest);
        cartServicePort.addItemsToCart(cart);
    }
}