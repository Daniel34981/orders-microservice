package com.ecommerce.orders.domain.api;

import com.ecommerce.orders.domain.model.Auth;
import com.ecommerce.orders.domain.model.UserAuth;

public interface IAuthServicePort {
    Auth loginUser(UserAuth userAuth);
}
