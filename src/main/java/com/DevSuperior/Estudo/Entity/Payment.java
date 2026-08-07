package com.DevSuperior.Estudo.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "tb_payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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
