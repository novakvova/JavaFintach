package org.example.dto.product;

import lombok.Data;

@Data
public class ProductImageDto {
    private Long id;
    private String name;
    private int priority;
    private Integer productId;
}
