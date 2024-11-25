package com.hexagonal.microservice_cart.infrastructure.output.jpa.adapter;

import com.hexagonal.microservice_cart.domain.model.Cart;
import com.hexagonal.microservice_cart.domain.spi.ICartPersistencePort;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.entity.CartEntity;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.mapper.CartEntityMapper;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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

    @Override
    public List<Cart> getCartItemsByUserId(Long userId) {
        List<CartEntity> cartEntities = cartRepository.findItemsByUserId(userId);
        return cartEntities.stream()
                .map(cartEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void removeItemFromCart(Cart cart) {
        CartEntity cartEntity = cartEntityMapper.toEntity(cart);
        cartRepository.delete(cartEntity);
    }

    @Override
    public List<Cart> getCartByUserId(Long userId) {
        List<CartEntity> cartEntities = cartRepository.findItemsByUserId(userId);
        return cartEntities.stream()
                .map(cartEntityMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CartEntity findProductByUserIdAndProductId(Long userId, Long articleId) {
        return cartRepository.findByUserIdAndArticleId(userId, articleId);
    }

    @Override
    public void clearCartByUserId(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}
