package com.DevSuperior.Estudo.Service;


import com.DevSuperior.Estudo.DTO.ProductDTO;
import com.DevSuperior.Estudo.Entity.Product;
import com.DevSuperior.Estudo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(product -> new ProductDTO(product));
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = new Product();
        copyDtoToEntity(productDTO, product);
        productRepository.save(product);

        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(ProductDTO productDTO, Long id) {
        Product product = productRepository.getReferenceById(id); // Não comunica diretamente com o Banco de Dados
        copyDtoToEntity(productDTO, product);
        productRepository.save(product);
        return new ProductDTO(product);
    }

    private void copyDtoToEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(product.getImgUrl());
        productRepository.save(product);
    }

}
