package com.hexagonal.microservice_cart.domain.spi;

import com.hexagonal.microservice_cart.domain.model.Cart;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.entity.CartEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICartPersistencePort {

    void saveCart(Cart cart);

    void createCart(Cart cart);

    void addItemsToCart(Cart cart);

    List<Cart> getCartItemsByUserId(Long userId);

    void removeItemFromCart(Cart cart);

    Page<CartEntity> getCartByUserId(Long userId, int page, int size, String sortBy, boolean sortDirection);

    CartEntity findProductByUserIdAndProductId(Long userId, Long articleId);
}
