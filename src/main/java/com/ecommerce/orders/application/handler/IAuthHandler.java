package com.ecommerce.orders.application.handler;

import com.ecommerce.orders.application.dto.auth.AuthLoginRequestDto;
import com.ecommerce.orders.application.dto.auth.AuthResponseDto;
import jakarta.validation.Valid;

public interface IAuthHandler {
    AuthResponseDto loginUser(@Valid AuthLoginRequestDto userRequest);
}
