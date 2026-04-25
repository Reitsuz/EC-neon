package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("products", repo.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String add(Product product) {
        repo.save(product);
        return "redirect:/list";
    }
}
