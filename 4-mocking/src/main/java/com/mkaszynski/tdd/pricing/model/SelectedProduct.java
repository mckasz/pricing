package com.mkaszynski.tdd.pricing.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
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
