package com.hexagonal.microservice_cart.infrastructure.input.rest;

import com.hexagonal.microservice_cart.application.dto.CartRequest;
import com.hexagonal.microservice_cart.application.handler.CartHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.NoSuchElementException;

import static com.hexagonal.microservice_cart.constants.ValidationConstants.*;

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
}
