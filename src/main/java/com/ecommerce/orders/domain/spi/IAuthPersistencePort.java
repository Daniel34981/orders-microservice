package com.ecommerce.orders.domain.spi;

import com.ecommerce.orders.domain.model.Auth;

import java.util.List;

public interface IAuthPersistencePort {
    Auth loginUser(String username, List<String> auths);
}
