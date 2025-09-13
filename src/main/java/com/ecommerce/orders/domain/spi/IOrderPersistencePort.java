package com.ecommerce.orders.domain.spi;

import com.ecommerce.orders.domain.model.Order;

public interface IOrderPersistencePort {
    Long createOrder(Order order);
}
