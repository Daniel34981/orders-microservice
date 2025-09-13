package com.ecommerce.orders.domain.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;
    private String name;
    private String description;
}
