package org.example.mapper;

import org.example.dto.category.CategoryItemDTO;
import org.example.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "creationTime", target = "dateCreated", dateFormat = "yyyy-MM-dd HH:mm:ss")
    CategoryItemDTO toDto(CategoryEntity category);
}