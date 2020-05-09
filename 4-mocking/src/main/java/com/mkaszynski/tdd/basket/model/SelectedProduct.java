package com.mkaszynski.tdd.basket.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SelectedProduct {
    private String name;
    private int price;
    private int quantity;
    private ProductType type;

    public SelectedProduct(String name, int price, int quantity, ProductType type) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }

    public int quantity() {
        return quantity;
    }
}
