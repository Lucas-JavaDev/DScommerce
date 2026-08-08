package com.DevSuperior.Estudo.Service;


import com.DevSuperior.Estudo.DTO.ProductDTO;
import com.DevSuperior.Estudo.Entity.Product;
import com.DevSuperior.Estudo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)  // Boa prática
    public ProductDTO findById(Long id) {
        Optional<Product> opProduct = productRepository.findById(id);
        Product product = opProduct.get();

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl()
        );
    }

}
