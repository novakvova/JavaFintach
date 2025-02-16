package org.example.service;

import org.example.dto.category.CategoryEditDTO;
import org.example.dto.category.CategoryPostDTO;
import org.example.entities.CategoryEntity;
import org.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FileService fileService;

    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }

    public Optional<CategoryEntity> getById(Integer id) {
        return categoryRepository.findById(id);
    }

    public CategoryEntity create(CategoryPostDTO categoryPostDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryPostDTO.getName());
        var imgName = fileService.load(categoryPostDTO.getImageFile());
        categoryEntity.setImage(imgName);
        categoryEntity.setDescription(categoryPostDTO.getDescription());
        categoryEntity.setCreationTime(LocalDateTime.now());
        return categoryRepository.save(categoryEntity);
    }

    public CategoryEntity edit(CategoryEditDTO dto) {
        CategoryEntity entity = categoryRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (dto.getName() != null && !dto.getName().isBlank()) {
            entity.setName(dto.getName());
        }

        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            entity.setDescription(dto.getDescription());
        }

        var newImageFile = dto.getImageFile();
        if (newImageFile!=null && !newImageFile.isEmpty()){
            var newImagePath = fileService.replace(entity.getImage(), dto.getImageFile());
            entity.setImage(newImagePath);
        }

        return categoryRepository.save(entity);
    }

    public boolean delete(Integer id) {
        var res = getById(id);
        if (res.isEmpty()){
            return false;
        }
        fileService.remove(res.get().getImage());
        categoryRepository.deleteById(id);
        return true;
    }
}

