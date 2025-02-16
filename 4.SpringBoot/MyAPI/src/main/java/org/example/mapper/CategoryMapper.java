package org.example.mapper;

import org.example.dto.category.CategoryItemDTO;
import org.example.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "creationTime", target = "dateCreated", dateFormat = "yyyy-MM-dd HH:mm:ss")
    CategoryItemDTO toDto(CategoryEntity category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "creationTime", expression = "java(java.time.LocalDateTime.now())")
    CategoryEntity fromPostDto(org.example.dto.category.CategoryPostDTO dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    CategoryEntity fromEditDto(org.example.dto.category.CategoryEditDTO dto);
}