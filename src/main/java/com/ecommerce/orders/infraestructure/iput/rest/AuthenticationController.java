package com.ecommerce.orders.infraestructure.iput.rest;

import com.ecommerce.orders.application.dto.auth.AuthLoginRequestDto;
import com.ecommerce.orders.application.dto.auth.AuthResponseDto;
import com.ecommerce.orders.application.handler.IAuthHandler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@PreAuthorize("permitAll()")
public class AuthenticationController {

    private final IAuthHandler authHandler;

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid AuthLoginRequestDto userRequest){
        return new ResponseEntity<>(authHandler.loginUser(userRequest), HttpStatus.OK);
    }

}
