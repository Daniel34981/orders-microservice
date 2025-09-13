package com.ecommerce.orders.application.handler;

import com.ecommerce.orders.application.dto.auth.AuthLoginRequestDto;
import com.ecommerce.orders.application.dto.auth.AuthResponseDto;
import com.ecommerce.orders.application.mapper.AuthRequestMapper;
import com.ecommerce.orders.application.mapper.AuthResponseMapper;
import com.ecommerce.orders.domain.api.IAuthServicePort;
import com.ecommerce.orders.domain.model.Auth;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthListHandler implements IAuthHandler{

    private final AuthRequestMapper authRequestMapper;
    private final AuthResponseMapper authResponseMapper;
    private final IAuthServicePort authServicePort;

    @Override
    public AuthResponseDto loginUser(AuthLoginRequestDto userRequest) {
        Auth auth = authServicePort.loginUser(authRequestMapper.toUserAuth(userRequest));
        return authResponseMapper.toResponse(auth);
    }
}
