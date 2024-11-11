package com.hexagonal.microservice_cart.infrastructure.input.rest;

import com.hexagonal.microservice_cart.application.dto.ArticleResponse;
import com.hexagonal.microservice_cart.application.dto.CartRequest;
import com.hexagonal.microservice_cart.application.handler.CartHandler;
import com.hexagonal.microservice_cart.domain.api.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.NoSuchElementException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import static com.hexagonal.microservice_cart.constants.ValidationConstants.*;

@RestController
@RequestMapping(CARTS_URL)
@RequiredArgsConstructor
public class CartRestController {

    private final CartHandler cartHandler;
    private final ArticleService articleService;

    @PostMapping(BASE_URL)
    @PreAuthorize(ROL_CUSTOMER)
    public ResponseEntity<?> saveCartIn(@Valid @RequestBody CartRequest cartRequest) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            System.out.println("Username from JWT: " + username);

            ArticleResponse article = articleService.getArticleById(cartRequest.getArticleId());

            if (article.getArticleStock() >= cartRequest.getQuantity()) {
                cartRequest.setUserId(Long.valueOf(username));
                cartHandler.saveCartIn(cartRequest);
                return ResponseEntity.ok(Collections.singletonMap(MESSAGE, CART_SAVED_SUCCESSFULLY));
            } else {
                LocalDate date = LocalDate.now();
                LocalDate date2 = date.plusDays(7);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Collections.singletonMap(MESSAGE, date2));
            }

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap(MESSAGE, ARTICLE_NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap(MESSAGE, AN_ERROR_OCCURRED));
        }
    }
}
