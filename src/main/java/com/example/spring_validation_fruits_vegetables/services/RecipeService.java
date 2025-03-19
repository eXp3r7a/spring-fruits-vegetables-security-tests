package com.example.spring_validation_fruits_vegetables.services;

import com.example.spring_validation_fruits_vegetables.entities.Product;
import com.example.spring_validation_fruits_vegetables.entities.Recipe;
import com.example.spring_validation_fruits_vegetables.repositories.ProductRepository;
import com.example.spring_validation_fruits_vegetables.repositories.RecipeRepository;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    private final ProductRepository productRepository;

    public RecipeService(RecipeRepository recipeRepository, ProductRepository productRepository) {
        this.recipeRepository = recipeRepository;
        this.productRepository = productRepository;
    }

    public String submitRecipe(@Valid @ModelAttribute Recipe recipe, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("recipe", new Recipe());
            model.addAttribute("products", productRepository.findAll());
            return "recipes/add_form";
        }

        recipeRepository.save(recipe);
        return "redirect:/results";
    }

    public String deleteRecipe(Model model, @RequestParam("recipe_id") Long recipeId) {
        recipeRepository.deleteById(recipeId);
        model.addAttribute("recipes", recipeRepository.findAll());
        model.addAttribute("recipeDelete", new Recipe());

        return "recipes/get_recipes";
    }

    public String updateRecipe(@Valid @ModelAttribute Recipe recipe, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("recipeEdit", recipe);
            return "recipes/edit_recipe";
        }

        recipeRepository.save(recipe);
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes/get_recipes";
    }

    public int calculateRecipeRate(Recipe recipe) {
        int recipeRate = 3; //default

        if (recipe.getCategory().equalsIgnoreCase("soup")) {
            return calculateRateForSoup(recipe.getProductList(), recipeRate);

        } else if (recipe.getCategory().equalsIgnoreCase("salad")){
            return calculateRateForSalad(recipe.getProductList(), recipeRate);

        } else if (recipe.getCategory().equalsIgnoreCase("dessert")) {
            return calculateRateForDessert(recipe.getProductList(), recipeRate);

        } else if (recipe.getCategory().equalsIgnoreCase("main")) {
            return calculateRateForMain(recipe.getProductList(), recipeRate);
        }
        return recipeRate;
    }

    private int calculateRateForMain(List<Product> productList, int recipeRate){
        for (Product product : productList){
            if(product.getCategory().equalsIgnoreCase("fruit")){
                recipeRate -= 2;
            } else if (product.getCategory().equalsIgnoreCase("vegetable")) {
                recipeRate += 1;
            }
            else if (product.getCategory().equalsIgnoreCase("spices")) {
                recipeRate += 1;
            }
        }
        return recipeRate;
    }

    private int calculateRateForDessert(List<Product> productList, int recipeRate){
        for (Product product : productList){
            if(product.getCategory().equalsIgnoreCase("fruit")){
                recipeRate += 2;
            } else if (product.getCategory().equalsIgnoreCase("vegetable")) {
                recipeRate -= 1;
            }
            else if (product.getCategory().equalsIgnoreCase("chocolate")) {
                recipeRate += 3;
            }
        }
        return recipeRate;
    }

    private int calculateRateForSalad(List<Product> productList, int recipeRate){
        for (Product product : productList){
            if(product.getCategory().equalsIgnoreCase("fruit")){
                recipeRate += 1;
            } else if (product.getCategory().equalsIgnoreCase("vegetable")) {
                recipeRate += 1;
            }
        }
        return recipeRate;
    }

    private int calculateRateForSoup(List<Product> productList, int recipeRate){
        for (Product product : productList){
            if(product.getCategory().equalsIgnoreCase("fruit")){
                recipeRate -= 2;
            } else if (product.getCategory().equalsIgnoreCase("vegetable")) {
                recipeRate += 1;
            }
        }
        return recipeRate;
    }

}
