package com.example.spring_validation_fruits_vegetables.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @NotEmpty(message = "Product name must not be empty! Please enter name.")
    private String name;

    @NotEmpty(message = "Category must not be unchecked! Please check category.")
    private String category; // fruit/vegetable/spices

    public Product(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Product() {
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
