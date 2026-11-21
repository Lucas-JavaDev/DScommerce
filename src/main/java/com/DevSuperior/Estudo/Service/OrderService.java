package com.DevSuperior.Estudo.Service;


import com.DevSuperior.Estudo.DTO.OrderDTO;
import com.DevSuperior.Estudo.DTO.OrderItemDTO;
import com.DevSuperior.Estudo.Entity.Enum.OrderStatus;
import com.DevSuperior.Estudo.Entity.Order;
import com.DevSuperior.Estudo.Entity.OrderItem;
import com.DevSuperior.Estudo.Entity.Product;
import com.DevSuperior.Estudo.Exception.ResourceNotFoundException;
import com.DevSuperior.Estudo.Repository.OrderItemRepository;
import com.DevSuperior.Estudo.Repository.OrderRepository;
import com.DevSuperior.Estudo.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    private final UserService userService;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final AuthService authService;
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository, UserService userService, ProductRepository productRepository, OrderItemRepository orderItemRepository, AuthService authService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.authService = authService;
    }





    @Transactional(readOnly = true)  // Boa prática
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found")
        );
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        Order order = new Order();

        copyDtoToEntity(orderDTO, order);

        return new OrderDTO(order);
    }


    private void copyDtoToEntity(OrderDTO orderDTO, Order order) {
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(userService.autenticated());
        for(OrderItemDTO item : orderDTO.getItems()){
            Product product = productRepository.getReferenceById(item.getProductId());
            OrderItem orderItem = new OrderItem(order, product, product.getPrice(), item.getQuantity());
            order.getItems().add(orderItem);

        }
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());

    }


}
