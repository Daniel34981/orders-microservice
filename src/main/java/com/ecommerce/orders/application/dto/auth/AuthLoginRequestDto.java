package com.ecommerce.orders.application.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.ecommerce.orders.domain.constants.AuthErrorMessages.*;


@Getter
@Setter
@AllArgsConstructor
public class AuthLoginRequestDto {
    @NotBlank(message = USER_REQUIRED)
    @Email(message = EMAIL_ERROR)
    private String username;
    @NotBlank(message = PASSWORD_REQUIRED)
    @Size(min = 6, message = PASSWORD_ERROR)
    private String password;
}
