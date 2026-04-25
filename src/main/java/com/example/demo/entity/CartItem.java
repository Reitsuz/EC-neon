package com.example.demo.entity;

import lombok.*;

@Getter
@Setter
public class CartItem {
    private Long productId;
    private String name;
    private int price;
    private int quantity;
}
