package com.DevSuperior.Estudo.DTO;


import com.DevSuperior.Estudo.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;


    public CategoryDTO(Category category) {
        id = category.getId();
        name = category.getName();
    }




}
