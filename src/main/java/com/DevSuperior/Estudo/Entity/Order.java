package com.DevSuperior.Estudo.Entity;


import com.DevSuperior.Estudo.Entity.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "tb_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // Indica ao banco, configurando como default UTC, sem time zone.
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIMEZONE")
    private Instant moment; // Indica o instante que o pedido foi realizado.
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

}
