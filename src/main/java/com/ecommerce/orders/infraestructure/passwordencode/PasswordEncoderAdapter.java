package com.ecommerce.orders.infraestructure.passwordencode;


import com.ecommerce.orders.domain.spi.IUserPasswordEncoderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class PasswordEncoderAdapter implements IUserPasswordEncoderPort {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
