package com.example.spring_validation_fruits_vegetables.dto;

import com.example.spring_validation_fruits_vegetables.entities.Product;
import com.example.spring_validation_fruits_vegetables.entities.Recipe;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRecipeEditDto {

    private Recipe recipe;

    private List<Product> productList;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
