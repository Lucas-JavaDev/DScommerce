package com.DevSuperior.Estudo.Service;


import com.DevSuperior.Estudo.DTO.OrderDTO;
import com.DevSuperior.Estudo.Entity.Order;
import com.DevSuperior.Estudo.Exception.ResourceNotFoundException;
import com.DevSuperior.Estudo.Repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }





    @Transactional(readOnly = true)  // Boa prática
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found")
        );
        return new OrderDTO(order);
    }


}
