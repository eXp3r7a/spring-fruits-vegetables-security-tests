package com.example.spring_validation_fruits_vegetables.controllers;

import com.example.spring_validation_fruits_vegetables.entities.Recipe;
import com.example.spring_validation_fruits_vegetables.repositories.ProductRepository;
import com.example.spring_validation_fruits_vegetables.repositories.RecipeRepository;
import com.example.spring_validation_fruits_vegetables.services.RecipeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/recipes")
public class RecipeController implements WebMvcConfigurer {

    private final RecipeRepository recipeRepository;

    private final ProductRepository productRepository;

    private final RecipeService recipeService;

    public RecipeController(RecipeRepository recipeRepository, ProductRepository productRepository, RecipeService recipeService){
        this.recipeRepository= recipeRepository;
        this.productRepository = productRepository;
        this.recipeService = recipeService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("result");
    }

    @GetMapping("/add")
    public String addRecipeForm(Model model){
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("products", productRepository.findAll());
        return "recipes/add_form";
    }

    @PostMapping("/submit")
    public String submitRecipe(@Valid @ModelAttribute Recipe recipe, BindingResult bindingResult, Model model){
        return recipeService.submitRecipe(recipe,bindingResult,model);
    }

    @PostMapping("/delete")
    public String deleteRecipe(Model model, @RequestParam("recipe_id") Long recipeId){
        return recipeService.deleteRecipe(model, recipeId);
    }

    @GetMapping("/edit")
    public String editRecipe(Model model, @RequestParam("recipe_id") Long recipeId){
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("recipeEdit", recipeRepository.findById(recipeId));
        return "recipes/edit_recipe";
    }

    @PostMapping("/update")
    public String updateRecipe(@Valid @ModelAttribute Recipe recipe, BindingResult bindingResult, Model model){
        return recipeService.updateRecipe(recipe,bindingResult,model);
    }

    @GetMapping("/get")
    public String getAllRecipes(Model model){
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes/get_recipes";
    }
}
