package com.ecommerce.orders.domain.spi;

public interface IUserPasswordEncoderPort {
    String encodePassword(String password);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
