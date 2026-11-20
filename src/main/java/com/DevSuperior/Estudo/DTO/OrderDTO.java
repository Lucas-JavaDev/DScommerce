package com.DevSuperior.Estudo.DTO;


import com.DevSuperior.Estudo.Entity.Enum.OrderStatus;
import com.DevSuperior.Estudo.Entity.Order;
import com.DevSuperior.Estudo.Entity.OrderItem;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;

    private ClientDTO client;

    private PaymentDTO payment;

    @NotEmpty(message = "Deve ter pelo meno um item")
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(ClientDTO client, Long id, Instant moment, PaymentDTO payment, OrderStatus status) {
        this.client = client;
        this.id = id;
        this.moment = moment;
        this.payment = payment;
        this.status = status;
    }

    public OrderDTO(Order order) {
        id = order.getId();
        moment = order.getMoment();
        status = order.getStatus();
        client = new ClientDTO(order.getClient());
        payment = (order.getPayment() == null) ? null : new PaymentDTO(order.getPayment());
        for(OrderItem item : order.getItems()){
            items.add(new OrderItemDTO(item));
        }
    }


    public Double getTotal() {
        double sum = 0;
        for (OrderItemDTO item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }
}
