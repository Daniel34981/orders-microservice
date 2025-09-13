package com.ecommerce.orders.domain.spi;

public interface IStockPersistencePort {
    void decrease(Long productId, int qty);
}
