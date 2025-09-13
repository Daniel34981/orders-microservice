package com.ecommerce.orders.domain.api;

import com.ecommerce.orders.domain.model.Order;

public interface IOrderServicePort {
    void createOrder(Order order);
}

