package com.example.spring_validation_fruits_vegetables.controllers;

import com.example.spring_validation_fruits_vegetables.dto.ProductRecipeEditDto;
import com.example.spring_validation_fruits_vegetables.entities.Recipe;
import com.example.spring_validation_fruits_vegetables.repositories.ProductRepository;
import com.example.spring_validation_fruits_vegetables.repositories.RecipeRepository;
import com.example.spring_validation_fruits_vegetables.services.RecipeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipes/rest")
public class RecipeRestController {

    private final RecipeRepository recipeRepository;

    private final ProductRepository productRepository;

    public RecipeRestController(RecipeRepository recipeRepository, ProductRepository productRepository){
        this.recipeRepository= recipeRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/get")
    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }

    @GetMapping("/edit")
    public ProductRecipeEditDto editRecipe(@RequestParam("recipe_id") Long recipeId){
        ProductRecipeEditDto productRecipeEditDto = new ProductRecipeEditDto();
        productRecipeEditDto.setRecipe(recipeRepository.findById(recipeId).orElseThrow());
        productRecipeEditDto.setProductList(productRepository.findAll());
        return productRecipeEditDto;
    }
}
