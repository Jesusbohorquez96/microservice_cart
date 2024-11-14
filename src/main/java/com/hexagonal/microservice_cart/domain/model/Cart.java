package com.hexagonal.microservice_cart.domain.model;

import java.time.LocalDateTime;

public class Cart {

    private Long id;
    private Long userId;
    private Long articleId;
    private Integer quantity;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    private String articleName;
    private String articleDescription;
    private Integer articleStock;
    private Double articlePrice;

    public Cart(Long id, Long userId, Long articleId, Integer quantity, LocalDateTime creationDate, LocalDateTime updateDate, String articleName, String articleDescription, Integer articleStock, Double articlePrice) {
        this.id = id;
        this.userId = userId;
        this.articleId = articleId;
        this.quantity = quantity;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.articleName = articleName;
        this.articleDescription = articleDescription;
        this.articleStock = articleStock;
        this.articlePrice = articlePrice;
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

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public Integer getArticleStock() {
        return articleStock;
    }

    public void setArticleStock(Integer articleStock) {
        this.articleStock = articleStock;
    }

    public Double getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(Double articlePrice) {
        this.articlePrice = articlePrice;
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
                ", articleName='" + articleName + '\'' +
                ", articleDescription='" + articleDescription + '\'' +
                ", articleStock=" + articleStock +
                ", articlePrice=" + articlePrice +
                '}';
    }
}
