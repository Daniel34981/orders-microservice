package com.ecommerce.orders.infraestructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "pedidos_productos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductEntity {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_pedido", nullable = false)
    private Long orderId;

    @Column(name = "id_producto", nullable = false)
    private Long productId;

    @Column(name = "cantidad", nullable = false)
    private int quantity;

    @Column(name = "precio_total", nullable = false)
    private Double totalPrice;
}
