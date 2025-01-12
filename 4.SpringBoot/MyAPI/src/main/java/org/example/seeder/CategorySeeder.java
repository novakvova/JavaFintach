package org.example.seeder;

import org.example.entities.CategoryEntity;
import org.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;


@Component
public class CategorySeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    public void seed() {
        if (categoryRepository.count() == 0) {
            CategoryEntity category1 = new CategoryEntity();
            category1.setName("Electronics");
            category1.setDescription("Gadgets and devices");
            category1.setImage("electronics.jpg");
            category1.setCreationTime(LocalDateTime.now());

            CategoryEntity category2 = new CategoryEntity();
            category2.setName("Books");
            category2.setDescription("All kinds of books");
            category2.setImage("books.jpg");
            category2.setCreationTime(LocalDateTime.now());

            CategoryEntity category3 = new CategoryEntity();
            category3.setName("Clothing");
            category3.setDescription("Fashion and apparel");
            category3.setImage("clothing.jpg");
            category3.setCreationTime(LocalDateTime.now());

            // Зберігаємо дані до бази
            categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
        }
    }
}
