package com.example.spring_validation_fruits_vegetables.controllers;

import com.example.spring_validation_fruits_vegetables.entities.Product;
import com.example.spring_validation_fruits_vegetables.repositories.ProductRepository;
import com.example.spring_validation_fruits_vegetables.services.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/rest")
public class ProductRestController {

    private final ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping("/get")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/edit")
    public Product editProduct(@RequestParam("product_id") Long productId){
        //http://localhost:8080/products/rest/edit?product_id=1 test link
        return productRepository.findById(productId).orElseThrow();
    }

}
