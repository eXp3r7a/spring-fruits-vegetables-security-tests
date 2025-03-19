package com.example.spring_validation_fruits_vegetables;

import com.example.spring_validation_fruits_vegetables.entities.Product;
import com.example.spring_validation_fruits_vegetables.repositories.ProductRepository;
import com.example.spring_validation_fruits_vegetables.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import org.springframework.ui.Model;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;

    @Test
    void testSubmitProductWhenValid(){
        //GIVEN
        when(bindingResult.hasErrors()).thenReturn(false);
        Product product = new Product();

        //WHEN
        productService.submitProduct(product,bindingResult);

        //THEN
        verify(productRepository, times(1)).save(product);

    }

    @Test
    void testDeleteProductWhenValid(){
        //GIVEN
        Long productId = 2L;

        //WHEN
        productService.deleteProduct(model, productId);

        //THEN
        verify(productRepository,times(1)).deleteById(productId);
        verify(model,times(2)).addAttribute(anyString(),any());
    }

    @Test
    void testUpdateProductWhenErrors(){
        //GIVEN
        when(bindingResult.hasErrors()).thenReturn(true);
        Product product = new Product();

        //WHEN
        productService.updateProduct(product,bindingResult,model);

        //THEN
        verify(model,times(1)).addAttribute("productEdit",product);
        verify(productRepository, never()).save(any());
    }

    @Test
    void testUpdateProductWhenValid(){
        //GIVEN
        when(bindingResult.hasErrors()).thenReturn(false);
        Product product = new Product();

        //WHEN
        productService.updateProduct(product,bindingResult,model);

        //THEN
        verify(productRepository, times(1)).save(product);
        verify(model,times(1)).addAttribute(anyString(),any());

    }

}
