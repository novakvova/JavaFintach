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
    @Autowired
    private FileService fileService;

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryEntity createCategory(CategoryPostDTO categoryPostDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryPostDTO.getName());
        var imgName = fileService.load(categoryPostDTO.getImageFile());
        categoryEntity.setImage(imgName);
        categoryEntity.setDescription(categoryPostDTO.getDescription());
        categoryEntity.setCreationTime(LocalDateTime.now());
        return categoryRepository.save(categoryEntity);
    }
}

