package com.ecommerce.orders.infraestructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // bigserial
    private Long id;

    // FK a usuario(id) — lo mapeamos como scalar para evitar dependencia a UsuarioEntity
    @Column(name = "id_usuario", nullable = false)
    private Long userid;

    @Column(name = "monto_total", nullable = false)
    private Double totalPrice; // si prefieres precisión, cambia a BigDecimal

    // FK a estado(id) — catálogo de estados (CREATED, PAID, ...)
    @Column(name = "estado", nullable = false)
    private Long status;

    @Column(name = "direccion_envio")
    private String address;

    @Column(name = "metodo_de_pago")
    private String paidMethod;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDate orderDate;

    @Column(name = "fecha_final")
    private LocalDate finalDate;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}
