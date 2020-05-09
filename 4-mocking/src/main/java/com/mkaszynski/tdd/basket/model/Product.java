package com.mkaszynski.tdd.basket.model;

import lombok.Value;

@Value
public class Product {
    private final String name;
    private final int price;
    private final ProductType type;

    boolean isFood() {
        return getType() == ProductType.FOOD;
    }

}
