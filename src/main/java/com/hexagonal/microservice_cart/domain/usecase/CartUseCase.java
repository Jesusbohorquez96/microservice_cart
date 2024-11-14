package com.hexagonal.microservice_cart.domain.usecase;

import com.hexagonal.microservice_cart.application.dto.CategoryResponse;
import com.hexagonal.microservice_cart.domain.api.ArticleService;
import com.hexagonal.microservice_cart.domain.api.ICartServicePort;
import com.hexagonal.microservice_cart.domain.model.Cart;
import com.hexagonal.microservice_cart.domain.spi.ICartPersistencePort;
import com.hexagonal.microservice_cart.infrastructure.exception.InsufficientStockException;
import com.hexagonal.microservice_cart.infrastructure.output.jpa.entity.CartEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.hexagonal.microservice_cart.constants.ValidationConstants.*;

public class CartUseCase implements ICartServicePort {

    private final ICartPersistencePort cartPersistencePort;
    private final ArticleService articleService;

    public CartUseCase(ICartPersistencePort cartPersistencePort, ArticleService articleService) {
        this.cartPersistencePort = cartPersistencePort;
        this.articleService = articleService;
    }

    @Override
    public void saveCart(Cart cart) {

        List<String> articleCategories = articleService.getArticleById(cart.getArticleId()).getArticleCategories()
                .stream()
                .map(CategoryResponse::getCategoryName)
                .collect(Collectors.toList());

        List<Cart> userCartItems = cartPersistencePort.getCartItemsByUserId(cart.getUserId());

        Map<String, Long> categoryCountMap = userCartItems.stream()
                .flatMap(item -> articleService.getArticleById(item.getArticleId()).getArticleCategories().stream())
                .map(CategoryResponse::getCategoryName)
                .collect(Collectors.groupingBy(category -> category, Collectors.counting()));

        for (String category : articleCategories) {
            long currentCount = categoryCountMap.getOrDefault(category, ZERO_L);
            if (currentCount >= THREE) {
                throw new IllegalArgumentException(CATEGORY_LIMIT + category);
            }
        }
        int availableStock = articleService.getArticleById(cart.getArticleId()).getArticleStock();
        if (cart.getQuantity() > availableStock) {
            throw new InsufficientStockException(ARTICLE_STOCK_INSUFFICIENT + cart.getArticleId());
        }

        cartPersistencePort.addItemsToCart(cart);
    }

    @Override
    public void removeItemFromCart(Long userId, Long articleId) {
        List<Cart> userCartItems = cartPersistencePort.getCartItemsByUserId(userId);

        Cart itemToRemove = userCartItems.stream()
                .filter(item -> item.getArticleId().equals(articleId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(ARTICLE_NOT_IN_CART));

        cartPersistencePort.removeItemFromCart(itemToRemove);
    }

    @Override
    public Page<CartEntity> getCartByUserId(int userId, int page, int size, String sortBy, boolean ascending) {

        Sort sort = Sort.by(USER_ID);
        if (Boolean.parseBoolean(String.valueOf(ascending))) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }
        return cartPersistencePort.getCartByUserId(userId, page, size, sortBy, ascending);
    }
}
