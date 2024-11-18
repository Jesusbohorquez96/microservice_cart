package com.hexagonal.microservice_cart.application.handler;

import com.hexagonal.microservice_cart.application.dto.CartRequest;
import com.hexagonal.microservice_cart.application.dto.CartResponse;
import org.springframework.data.domain.Page;

public interface ICartHandler {

    void saveCartIn(CartRequest cartRequest);

    Page<CartResponse> getCartByUserId(Long userId, int page, int size, String sortBy, String sortDirection);

    Page<CartResponse> getFilteredArticles(int page, int size, String sortBy, String sortDirection, String categoryName, String brandName);
}
 