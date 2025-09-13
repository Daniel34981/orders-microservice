package com.ecommerce.orders.domain.spi;

import com.ecommerce.orders.domain.model.User;

public interface IUserPersistencePort {
    User getUserByEmail(String email);
}

