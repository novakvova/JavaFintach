package org.example.controller;


import org.example.dto.category.CategoryItemDTO;
import org.example.dto.category.CategoryPostDTO;
import org.example.dto.product.ProductItemDTO;
import org.example.dto.product.ProductPostDTO;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductItemDTO> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductItemDTO> getById(@PathVariable Integer id) {
        var model = productService.getById(id);
        return ResponseEntity.ok(model);
    }

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductItemDTO> create(@ModelAttribute ProductPostDTO dto) {
        var result = productService.create(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
