package org.example.service;

import org.example.dto.category.CategoryEditDTO;
import org.example.dto.category.CategoryItemDTO;
import org.example.dto.category.CategoryPostDTO;
import org.example.entities.CategoryEntity;
import org.example.mapper.CategoryMapper;
import org.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryItemDTO> getAll() {
        return categoryRepository.findAll()
                .stream().map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<CategoryItemDTO> getById(Integer id) {
        return categoryRepository.findById(id).map(categoryMapper::toDto);
    }

    public CategoryItemDTO create(CategoryPostDTO categoryPostDTO) {
        CategoryEntity categoryEntity = categoryMapper.fromPostDto(categoryPostDTO);
        var imgName = fileService.load(categoryPostDTO.getImageFile());
        categoryEntity.setImage(imgName);
        categoryEntity = categoryRepository.save(categoryEntity);
        return categoryMapper.toDto(categoryEntity);
    }

    public CategoryItemDTO edit(CategoryEditDTO dto) {
        CategoryEntity entity = categoryRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (dto.getName() != null && !dto.getName().isBlank()) {
            entity.setName(dto.getName());
        }

        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            entity.setDescription(dto.getDescription());
        }

        var newImageFile = dto.getImageFile();
        if (newImageFile != null && !newImageFile.isEmpty()) {
            var newImagePath = fileService.replace(entity.getImage(), dto.getImageFile());
            entity.setImage(newImagePath);
        }

        entity = categoryRepository.save(entity);
        return categoryMapper.toDto(entity);
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

