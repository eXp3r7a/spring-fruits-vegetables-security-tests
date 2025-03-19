package com.example.spring_validation_fruits_vegetables;

import com.example.spring_validation_fruits_vegetables.entities.Product;
import com.example.spring_validation_fruits_vegetables.entities.Recipe;
import com.example.spring_validation_fruits_vegetables.repositories.ProductRepository;
import com.example.spring_validation_fruits_vegetables.repositories.RecipeRepository;
import com.example.spring_validation_fruits_vegetables.services.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {
    @InjectMocks
    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;

    @Test
    void testSubmitRecipeWhenErrors(){
        //GIVEN
        when(bindingResult.hasErrors()).thenReturn(true);
        Recipe recipe = new Recipe();

        //WHEN
        String returnView = recipeService.submitRecipe(recipe, bindingResult, model);

        //THEN
        Assertions.assertEquals("recipes/add_form", returnView);
        verify(model,times(2)).addAttribute(anyString(),any());
        verify(recipeRepository,never()).save(any());
    }

    @Test
    void testSubmitRecipeWhenValid(){
        //GIVEN
        when(bindingResult.hasErrors()).thenReturn(false);
        Recipe recipe = new Recipe();

        //WHEN
        String returnView = recipeService.submitRecipe(recipe, bindingResult, model);

        //THEN
        Assertions.assertEquals("redirect:/results", returnView);
        verify(recipeRepository,times(1)).save(recipe);
    }

    @Test
    void testDeleteRecipe(){
        //GIVEN
        Long recipeId = 1L;

        //WHEN
        String returnView = recipeService.deleteRecipe(model, recipeId);

        //THEN
        Assertions.assertEquals("recipes/get_recipes", returnView);
        verify(recipeRepository,times(1)).deleteById(recipeId);
        verify(model,times(2)).addAttribute(anyString(), any());
    }

    @Test
    void testUpdateRecipeWhenErrors(){
        //GIVEN
        when(bindingResult.hasErrors()).thenReturn(true);
        Recipe recipe = new Recipe();

        //WHEN
        String returnView = recipeService.updateRecipe(recipe,bindingResult,model);

        //THEN
        Assertions.assertEquals("recipes/edit_recipe", returnView);
        verify(model,times(2)).addAttribute(anyString(),any());
        verify(recipeRepository,never()).save(any());
    }

    @Test
    void testUpdateRecipeWhenValid(){
        //GIVEN
        when(bindingResult.hasErrors()).thenReturn(false);
        Recipe recipe = new Recipe();

        //WHEN
        String returnView = recipeService.updateRecipe(recipe, bindingResult, model);

        //THEN
        Assertions.assertEquals("recipes/get_recipes", returnView);
        verify(recipeRepository, times(1)).save(recipe);
        verify(model,times(1)).addAttribute(anyString(),any());
    }

    @Test
    void testRateCalculateWhenSoup(){
        //GIVEN
        Recipe recipe = new Recipe();
        recipe.setCategory("Soup");

        Product product = new Product();
        product.setCategory("Fruit");

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        recipe.setProductList(productList);
        //WHEN
        int returnedRate = recipeService.calculateRecipeRate(recipe);

        //THEN
        Assertions.assertEquals(1,returnedRate);

    }

    @Test
    void testRateCalculateWhenDessert(){
        //GIVEN
        Recipe recipe = new Recipe();
        recipe.setCategory("Dessert");

        Product product1 = new Product();
        product1.setCategory("Fruit");
        Product product2 = new Product();
        product2.setCategory("Chocolate");

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        recipe.setProductList(productList);
        //WHEN
        int returnedRate = recipeService.calculateRecipeRate(recipe);

        //THEN
        //Default rate is 3 + 2(for fruit) + 3(for chocolate) = 7
        Assertions.assertEquals(8,returnedRate);
    }

    @Test
    void testRateCalculateWhenMain(){
        //GIVEN
        Recipe recipe = new Recipe();
        recipe.setCategory("Main");

        Product product1 = new Product();
        product1.setCategory("Vegetable");
        Product product2 = new Product();
        product2.setCategory("Spices");

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        recipe.setProductList(productList);
        //WHEN
        int returnedRate = recipeService.calculateRecipeRate(recipe);

        //THEN
        //Default rate is 3 + 1(for Vegetable) + 1(for Spices) = 5
        Assertions.assertEquals(5,returnedRate);
    }
}
