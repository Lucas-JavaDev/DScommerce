package com.DevSuperior.Estudo.DTO;


import com.DevSuperior.Estudo.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMinDTO {

    private Long id;
    private String name;
    private Double price;
    private String imgUrl;


    public ProductMinDTO(Product product) {
        id =  product.getId();
        name = product.getName();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
    }



}
