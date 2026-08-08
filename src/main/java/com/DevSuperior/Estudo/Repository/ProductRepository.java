package com.DevSuperior.Estudo.Repository;

import com.DevSuperior.Estudo.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
