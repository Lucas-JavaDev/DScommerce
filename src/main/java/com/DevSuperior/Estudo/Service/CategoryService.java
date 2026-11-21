package com.DevSuperior.Estudo.Service;

import com.DevSuperior.Estudo.DTO.CategoryDTO;
import com.DevSuperior.Estudo.Entity.Category;
import com.DevSuperior.Estudo.Repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> new CategoryDTO(category)).toList();
    }


}
