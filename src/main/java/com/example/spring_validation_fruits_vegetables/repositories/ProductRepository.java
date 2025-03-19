package com.example.spring_validation_fruits_vegetables.repositories;

import com.example.spring_validation_fruits_vegetables.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
