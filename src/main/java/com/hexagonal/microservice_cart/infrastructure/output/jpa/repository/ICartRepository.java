package com.hexagonal.microservice_cart.infrastructure.output.jpa.repository;

import com.hexagonal.microservice_cart.infrastructure.output.jpa.entity.CartEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findById(Long cartId);

    List<CartEntity> findByUserId(Long userId);

    Page<CartEntity> findByUserId(Long userId, Pageable pageable);

    CartEntity findByUserIdAndArticleId(Long userId, Long articleId);
}
