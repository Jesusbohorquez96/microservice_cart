package com.hexagonal.microservice_cart.infrastructure.output.jpa.adapter;

import com.hexagonal.microservice_cart.domain.model.Cart;
import com.hexagonal.microservice_cart.domain.spi.ICartPersistencePort;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.entity.CartEntity;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.mapper.CartEntityMapper;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        List<CartEntity> cartEntities = cartRepository.findByUserId(userId);
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
    public Page<CartEntity> getCartByUserId(int userId, int page, int size, String sortBy, boolean sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection ? "ASC" : "DESC");
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return cartRepository.findByUserId(userId, pageable);
    }
}
