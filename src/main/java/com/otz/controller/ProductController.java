package com.otz.controller;

import com.otz.entity.Product;
import com.otz.repo.ProductRepository;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product-list";
    }

    @GetMapping("/new")
    public String showForm(Product product) {
        return "product-form";
    }

    @PostMapping
    public String addProduct(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "product-form";
        }
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{pId}")
    public String deleteProduct(@PathVariable Integer pId) {
        productRepository.deleteById(pId);
        return "redirect:/products";
    }
}

