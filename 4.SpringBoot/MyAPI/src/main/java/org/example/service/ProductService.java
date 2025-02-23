package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.product.ProductItemDTO;
import org.example.mapper.ProductImageMapper;
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

}

