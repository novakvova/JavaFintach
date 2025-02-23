package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.product.ProductItemDTO;
import org.example.dto.product.ProductPostDTO;
import org.example.entities.CategoryEntity;
import org.example.entities.ProductEntity;
import org.example.entities.ProductImageEntity;
import org.example.mapper.ProductMapper;
import org.example.repository.ProductImageRepository;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private ProductImageRepository productImageRepository;
    private FileService fileService;
    private ProductMapper productMapper;

    public List<ProductItemDTO> getAll() {
        var list = productRepository.findAll();
        return productMapper.toDto(list);
    }

    public ProductItemDTO getById(Integer id) {
        return productMapper.toDto(productRepository.findById(id).orElse(null));
    }

    public ProductItemDTO create(ProductPostDTO dto) {
        ProductEntity entity = productMapper.fromPostDto(dto);
        var cat = new CategoryEntity();
        cat.setId(dto.getCategoryId());
        entity.setCategory(cat);
        productRepository.save(entity);
        int p=0;
        for(var img : dto.getImageFiles()) {
            var imgName = fileService.load(img);
            var entityImage = new ProductImageEntity();
            entityImage.setName(imgName);
            entityImage.setPriority(p);
            entityImage.setProduct(entity);
            productImageRepository.save(entityImage);
            p++;
        }
        return productMapper.toDto(entity);
    }

}

