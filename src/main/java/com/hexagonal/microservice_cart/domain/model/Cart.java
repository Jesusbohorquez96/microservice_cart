package com.hexagonal.microservice_cart.domain.model;

import java.time.LocalDateTime;

public class Cart {

    private Long id;
    private Long userId;
    private Long articleId;
    private Integer quantity;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    public Cart(Long id, Long userId, Long articleId, Integer quantity, LocalDateTime creationDate, LocalDateTime updateDate) {
        this.id = id;
        this.userId = userId;
        this.articleId = articleId;
        this.quantity = quantity;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", articleId=" + articleId +
                ", quantity=" + quantity +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
