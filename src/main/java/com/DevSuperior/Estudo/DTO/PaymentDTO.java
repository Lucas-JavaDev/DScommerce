package com.DevSuperior.Estudo.DTO;


import com.DevSuperior.Estudo.Entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;
    private Instant moment;

    public PaymentDTO(Payment payment) {
        id = payment.getId();
        moment = payment.getMoment();
    }



}
