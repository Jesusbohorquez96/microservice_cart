package com.hexagonal.microservice_cart.infrastructure.output.jpa.adapter;

import com.hexagonal.microservice_cart.domain.model.Cart;
import com.hexagonal.microservice_cart.domain.spi.ICartPersistencePort;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.entity.CartEntity;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.mapper.CartEntityMapper;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.repository.ICartRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartJpaAdapter implements ICartPersistencePort {

    private final ICartRepository cartRepository;
    private final CartEntityMapper cartEntityMapper;

    @Override
    public void saveCart(Cart cart) {
        CartEntity cartEntity = cartEntityMapper.toEntity(cart);
        cartRepository.save(cartEntity);
    }

    @Override
    public void createCart(Cart cart) {
        CartEntity cartEntity = cartEntityMapper.toEntity(cart);
        cartRepository.save(cartEntity);
    }

    @Override
    public void addItemsToCart(Cart cart) {
        CartEntity cartEntity = cartEntityMapper.toEntity(cart);
        cartRepository.save(cartEntity);
    }
}