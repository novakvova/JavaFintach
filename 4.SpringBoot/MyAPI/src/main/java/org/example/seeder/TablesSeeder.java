package org.example.seeder;

import org.example.entities.CategoryEntity;
import org.example.entities.ProductEntity;
import org.example.entities.ProductImageEntity;
import org.example.repository.CategoryRepository;
import org.example.repository.ProductImageRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Component
public class TablesSeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

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

            seedProducts(category1, category2, category3);
        }
    }

    private void seedProducts(CategoryEntity category1, CategoryEntity category2, CategoryEntity category3) {
        ProductEntity product1 = new ProductEntity();
        product1.setName("Smartphone");
        product1.setDescription("Latest model smartphone with high-end features");
        product1.setPrice(799.99);
        product1.setCategory(category1);
        product1.setCreationTime(LocalDateTime.now());

        ProductEntity product2 = new ProductEntity();
        product2.setName("Science Fiction Book");
        product2.setDescription("A thrilling sci-fi novel");
        product2.setPrice(19.99);
        product2.setCategory(category2);
        product2.setCreationTime(LocalDateTime.now());

        ProductEntity product3 = new ProductEntity();
        product3.setName("T-shirt");
        product3.setDescription("100% cotton unisex T-shirt");
        product3.setPrice(14.99);
        product3.setCategory(category3);
        product3.setCreationTime(LocalDateTime.now());

        productRepository.saveAll(List.of(product1, product2, product3));

        seedProductImages(product1, product2, product3);
    }

    private void seedProductImages(ProductEntity product1, ProductEntity product2, ProductEntity product3) {
        ProductImageEntity image1 = new ProductImageEntity();
        image1.setImageUrl("smartphone1.jpg");
        image1.setPriority(1);
        image1.setProduct(product1);

        ProductImageEntity image11 = new ProductImageEntity();
        image1.setImageUrl("smartphone11.jpg");
        image1.setPriority(2);
        image1.setProduct(product1);

        ProductImageEntity image2 = new ProductImageEntity();
        image2.setImageUrl("sci-fi-book.jpg");
        image2.setPriority(1);
        image2.setProduct(product2);

        ProductImageEntity image3 = new ProductImageEntity();
        image3.setImageUrl("tshirt1.jpg");
        image3.setPriority(1);
        image3.setProduct(product3);

        productImageRepository.saveAll(List.of(image1, image11, image2, image3));
    }
}
