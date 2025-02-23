package org.example.controller;


import org.example.dto.product.ProductItemDTO;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
