package com.ecommerce.orders.domain.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String phone;
    private LocalDate birthDate;
    private String email;
    private String password;
    private Role role;
}
