package com.hexagonal.microservice_cart.application.handler;

import com.hexagonal.microservice_cart.application.dto.CartRequest;
import com.hexagonal.microservice_cart.application.dto.CartResponse;
import com.hexagonal.microservice_cart.application.mapper.CartRequestMapper;
import com.hexagonal.microservice_cart.application.mapper.CartResponseMapper;
import com.hexagonal.microservice_cart.domain.api.ICartServicePort;
import com.hexagonal.microservice_cart.domain.model.Cart;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.entity.CartEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CartHandler implements ICartHandler {

    private final CartRequestMapper cartRequestMapper;
    private final ICartServicePort cartServicePort;
    private final CartResponseMapper cartResponseMapper;

    @Override
    public void saveCartIn(CartRequest cartRequest) {
        Cart cart = cartRequestMapper.toCart(cartRequest);
        cartServicePort.saveCart(cart);
    }

    @Override
    public Page<CartResponse> getCartByUserId(int userId, int page, int size, String sortBy, String sortDirection) {
        Page<CartEntity> cartEntities = cartServicePort.getCartByUserId(userId, page, size, sortBy, Boolean.parseBoolean(sortDirection));
        return cartEntities.map(cartResponseMapper::toCartResponse);
    }

    public void removeItemFromCart(Long userId, Long articleId) {
        cartServicePort.removeItemFromCart(userId, articleId);
    }
}