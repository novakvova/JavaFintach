package org.example.controller;

import org.example.dto.category.CategoryPostDTO;
import org.example.entities.CategoryEntity;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryEntity> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryPostDTO categoryPostDTO) {
        CategoryEntity category = categoryService.createCategory(categoryPostDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
}
