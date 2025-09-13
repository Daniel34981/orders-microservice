package com.ecommerce.orders.application.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDto {
    private String username;
    private String message;
    private String tokenJWT;
    private Boolean status;
}
