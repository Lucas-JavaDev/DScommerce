package com.DevSuperior.Estudo.Repository;

import com.DevSuperior.Estudo.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
