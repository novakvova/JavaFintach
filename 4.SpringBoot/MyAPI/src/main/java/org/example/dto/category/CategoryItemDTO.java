package org.example.dto.category;

import lombok.Data;

@Data
public class CategoryItemDTO {
    private Integer id;
    private String name;
    private String image;
    private String description;
    private String dateCreated;
}
