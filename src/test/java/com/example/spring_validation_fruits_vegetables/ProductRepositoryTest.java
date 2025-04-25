package com.example.spring_validation_fruits_vegetables;

import com.example.spring_validation_fruits_vegetables.entities.Product;
import com.example.spring_validation_fruits_vegetables.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles({"h2db"})
public class ProductRepositoryTest {

//    private final ProductRepository productRepository;
//
//    public ProductRepositoryTest(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    @Autowired
    ProductRepository productRepository;

    @Test
    void testSaveProductInDbAndReturnEntityWhenSuccess(){
        //GIVEN
        Product product = productRepository.save(new Product("Cumin", "Spices"));

        //WHEN
        Product checkProduct = productRepository.findById(product.getProduct_id()).orElse(null);

        //THEN
        Assertions.assertNotNull(checkProduct);
        Assertions.assertEquals(product.getName(), checkProduct.getName());
    }

    @Test
    public void testHowManyProductsHaveInTable(){
        List<Product> productList = productRepository.findAll();
        Assertions.assertEquals(16, productList.size());
    }
}
