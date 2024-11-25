package com.hexagonal.microservice_cart.application.handler;

import com.hexagonal.microservice_cart.application.dto.CartRequest;
import com.hexagonal.microservice_cart.application.dto.CartResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICartHandler {

    void saveCartIn(CartRequest cartRequest);

    List<CartResponse> getCartByUserId(Long userId);

    Page<CartResponse> getFilteredArticles(int page, int size, String sortBy, String sortDirection, String categoryName, String brandName);

    void clearCartByUserId(Long userId);

}
 