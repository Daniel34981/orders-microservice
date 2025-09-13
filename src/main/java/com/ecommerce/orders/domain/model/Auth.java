package com.ecommerce.orders.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    private String username;
    private String message;
    private String tokenJWT;
    private Boolean status;
}