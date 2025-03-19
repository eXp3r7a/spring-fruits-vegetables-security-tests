package com.example.spring_validation_fruits_vegetables.controllers;

import com.example.spring_validation_fruits_vegetables.entities.Product;
import com.example.spring_validation_fruits_vegetables.repositories.ProductRepository;
import com.example.spring_validation_fruits_vegetables.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping("/products")
public class ProductController implements WebMvcConfigurer {

    private final ProductRepository productRepository;

    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService){
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("result");
    }

    @GetMapping("/add")
    public String addProductForm(Model model){
        model.addAttribute("product", new Product());
        return "products/add_form";
    }

    @PostMapping("/submit")
    public String submitProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult){
        return productService.submitProduct(product,bindingResult);
    }

    @PostMapping("/delete")
    public String deleteProduct(Model model, @RequestParam("product_id") Long productId){
        return productService.deleteProduct(model, productId);
    }

    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model){
        return productService.updateProduct(product,bindingResult, model);
    }

    @GetMapping("/edit")
    public String editProduct(Model model, @RequestParam("product_id") Long productId){
        model.addAttribute("productEdit", productRepository.findById(productId));
        return "products/edit_product";
    }

    @GetMapping("/get")
    public String getAllProducts(Model model){
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("productDelete", new Product());
        return "products/get_products";
    }
}
