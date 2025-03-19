package com.example.spring_validation_fruits_vegetables.repositories;

import com.example.spring_validation_fruits_vegetables.entities.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
