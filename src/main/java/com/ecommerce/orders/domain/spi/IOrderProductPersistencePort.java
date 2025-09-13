package com.ecommerce.orders.domain.spi;

import com.ecommerce.orders.domain.model.OrderProduct;

public interface IOrderProductPersistencePort {
    void saveOrderItem(OrderProduct orderProduct);
}
