package com.ecommerce.orders.infraestructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String firstName;

    @Column(name = "apellido")
    private String lastName;

    @Column(name = "num_documento")
    private String documentNumber;

    @Column(name = "celular")
    private String phone;

    @Column(name = "fecha_nacimiento")
    private LocalDate birthDate;

    @Column(name ="correo", unique = true)
    private String email;

    @Column(name = "clave")
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    private RoleEntity role;

}
