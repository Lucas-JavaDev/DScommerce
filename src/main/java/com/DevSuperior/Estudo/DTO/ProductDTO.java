package com.DevSuperior.Estudo.DTO;

import com.DevSuperior.Estudo.Entity.Category;
import com.DevSuperior.Estudo.Entity.Product;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 3, max = 80, message = "Nome precisa conter 3-80 caracteres")
    private String name;

    @NotBlank
    @Size(min = 10, message = "Descrição precisa contar no minimo 10 caracteres")
    private String description;

    @NotNull(message = "Campo requerido")
    @Positive(message = "Preço deve ser positivo")
    private Double price;

    private String imgUrl;

    @NotEmpty(message = "Deve conter pelo menos uma categoria")
    private List<CategoryDTO> categories = new ArrayList<>();


    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
        for(Category category : product.getCategories()) {
            categories.add(new CategoryDTO(category));
        }
    }

}
