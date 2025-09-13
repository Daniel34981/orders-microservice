package com.ecommerce.orders.infraestructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_stock")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockEntity {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "available_qty", nullable = false)
    private Integer availableQty;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}
