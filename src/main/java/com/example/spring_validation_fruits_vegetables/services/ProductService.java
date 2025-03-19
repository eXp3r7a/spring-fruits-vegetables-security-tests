package com.example.spring_validation_fruits_vegetables.services;

import com.example.spring_validation_fruits_vegetables.entities.Product;
import com.example.spring_validation_fruits_vegetables.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public String submitProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "products/add_form";
        }

        productRepository.save(product);
        return "redirect:/results";
    }

    public String deleteProduct(Model model, @RequestParam("product_id") Long productId){
        productRepository.deleteById(productId);
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("productDelete", new Product());

        return "products/get_products";
    }

    public String updateProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("productEdit", product);
            return "products/edit_product";
        }

        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "products/get_products";
    }

}
