package org.example.service;

import org.example.dto.category.CategoryPostDTO;
import org.example.entities.CategoryEntity;
import org.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryEntity createCategory(CategoryPostDTO categoryPostDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryPostDTO.getName());
        categoryEntity.setImage(categoryPostDTO.getImage());
        categoryEntity.setDescription(categoryPostDTO.getDescription());
        categoryEntity.setCreationTime(LocalDateTime.now());

        return categoryRepository.save(categoryEntity);
    }
}

