package com.mkaszynski.tdd.basket.model;

import lombok.Value;

@Value
public class ProductKey {

    private final String name;
    private final int price;

    public ProductKey(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
