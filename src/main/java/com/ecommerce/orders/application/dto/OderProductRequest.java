package com.ecommerce.orders.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OderProductRequest {
    private Long productId;
    private int quantity;
    private double totalPrice;
}
