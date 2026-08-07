package com.DevSuperior.Estudo.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "tb_payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;


    // Relação com ORDER_ID, por conta do mapeamento
    // O MapsId, mapea o Id da ORDer, tendo como o Id payment
    // o Mesmo id da Order feita
    @OneToOne
    @MapsId
    private Order order;

}
