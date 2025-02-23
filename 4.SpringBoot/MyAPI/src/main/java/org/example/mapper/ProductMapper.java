package org.example.mapper;
import org.example.dto.product.ProductItemDTO;
import org.example.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "creationTime", target = "dateCreated", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "category.name", target = "categoryName")
    ProductItemDTO toDto(ProductEntity product);

    List<ProductItemDTO> toDto(List<ProductEntity> products);

}
