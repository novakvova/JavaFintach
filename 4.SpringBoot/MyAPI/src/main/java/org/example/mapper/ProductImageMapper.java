package org.example.mapper;

import org.example.dto.product.ProductImageDto;
import org.example.entities.ProductImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
    @Mapping(target = "productId", source = "product.id")
    ProductImageDto toDto(ProductImageEntity image);
    List<ProductImageDto> toDto(Iterable<ProductImageEntity> product);
}
