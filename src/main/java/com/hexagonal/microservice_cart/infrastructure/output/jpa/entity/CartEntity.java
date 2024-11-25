package com.hexagonal.microservice_cart.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import static com.hexagonal.microservice_cart.constants.ValidationConstants.*;

@Entity
@Table(name = CART, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_article", "id_user"})
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_article", nullable = false)
    private Long articleId;

    @Column(name = "id_user", nullable = false)
    private Long userId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "date_create", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "date_update", nullable = false)
    private LocalDateTime updateDate;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }

}

