package com.mkaszynski.tdd.pricing.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
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
