package com.hexagonal.microservice_cart.infrastructure.input.rest;

import com.hexagonal.microservice_cart.application.dto.CartRequest;
import com.hexagonal.microservice_cart.application.dto.CartResponse;
import com.hexagonal.microservice_cart.application.handler.CartHandler;
import com.hexagonal.microservice_cart.infrastructure.exception.InsufficientStockException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.NoSuchElementException;

import static com.hexagonal.microservice_cart.constants.ValidationConstants.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(CARTS_URL)
@RequiredArgsConstructor
public class CartRestController {

    private final CartHandler cartHandler;

    @PostMapping(BASE_URL)
    @PreAuthorize(ROL_CUSTOMER)
    public ResponseEntity<?> saveCartIn(@Valid @RequestBody CartRequest cartRequest) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Long userId = Long.valueOf(authentication.getName());
            cartRequest.setUserId(userId);

            cartHandler.saveCartIn(cartRequest);

            return ResponseEntity.ok(Collections.singletonMap(MESSAGE, CART_SAVED_SUCCESSFULLY));

        } catch (InsufficientStockException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap(MESSAGE, e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap(MESSAGE, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap(MESSAGE, AN_ERROR_OCCURRED));
        }
    }

    @DeleteMapping(BASE_URL + "{articleId}")
    @PreAuthorize(ROL_CUSTOMER)
    public ResponseEntity<?> removeItemFromCart(@PathVariable Long articleId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Long userId = Long.valueOf(authentication.getName());

            cartHandler.removeItemFromCart(userId, articleId);

            return ResponseEntity.ok(Collections.singletonMap(MESSAGE, ARTICLE_REMOVED_CART));

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap(MESSAGE, ARTICLE_NOT_IN_CART));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap(MESSAGE, DELETE_ARTICLE));
        }
    }

    @GetMapping(BASE_URL)
    @PreAuthorize(ROL_CUSTOMER)
    public ResponseEntity<Page<CartResponse>> getCartByUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            Long userId = Long.parseLong(authentication.getName());

            Page<CartResponse> cartItems = cartHandler.getCartByUserId(userId, page, size, sortBy, sortDirection);

            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/articles/filter")
    @PreAuthorize(ROL_CUSTOMER)
    public ResponseEntity<Page<CartResponse>> getArticlesByFilter(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String brandName
    ) {
        try {
            Page<CartResponse> articles = cartHandler.getFilteredArticles(page, size, sortBy, sortDirection, categoryName, brandName);
            return ResponseEntity.ok(articles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }
}
