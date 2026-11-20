package com.DevSuperior.Estudo.Repository;

import com.DevSuperior.Estudo.Entity.OrderItem;
import com.DevSuperior.Estudo.Entity.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
