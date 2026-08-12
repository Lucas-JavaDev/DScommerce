package com.DevSuperior.Estudo.DTO;

import com.DevSuperior.Estudo.Entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Campo Nulo")
    @Size(min = 3, max = 80, message = "Nome precisa conter 3-80 caracteres")
    private String name;

    @NotBlank
    @Size(min = 10, message = "Descrição precisa contar no minimo 10 caracteres")
    private String description;

    @Positive(message = "Preço deve ser positivo")
    private Double price;

    private String imgUrl;



    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
    }

}
