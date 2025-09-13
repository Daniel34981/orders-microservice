package com.ecommerce.orders.domain.api;

import com.ecommerce.orders.domain.model.User;

public interface IUserServicePort {
    User getUserByEmail(String email);
}
