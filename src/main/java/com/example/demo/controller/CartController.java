package com.example.demo.controller;

import com.example.demo.service.CartService;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductRepository productRepository;

    public CartController(CartService cartService, ProductRepository productRepository) {
        this.cartService = cartService;
        this.productRepository = productRepository;
    }

    // カート表示
    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("items", cartService.getItems());
        return "cart";
    }

    // 商品追加
    @GetMapping("/add/{id}")
    public String add(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(p -> {
                    cartService.add(p.getId(), p.getName(), p.getPrice());
                    return "redirect:/cart";
                })
                .orElse("redirect:/list");
    }

    // カートクリア
    @GetMapping("/clear")
    public String clear() {
        cartService.clear();
        return "redirect:/cart";
    }

    // チェックアウト画面
    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    // 購入完了
    @GetMapping("/success")
    public String success() {
        cartService.clear();
        return "success";
    }
}