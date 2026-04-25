package com.example.demo.service;

import com.example.demo.entity.CartItem;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
@SessionScope
public class CartService {

    private final Map<Long, CartItem> cart = new HashMap<>();

    public void add(Long id, String name, int price) {
        if (cart.containsKey(id)) {
            CartItem item = cart.get(id);
            item.setQuantity(item.getQuantity() + 1);
        } else {
            CartItem item = new CartItem();
            item.setProductId(id);
            item.setName(name);
            item.setPrice(price);
            item.setQuantity(1);
            cart.put(id, item);
        }
    }

    public Collection<CartItem> getItems() {
        return cart.values();
    }

    public void clear() {
        cart.clear();
    }
}
