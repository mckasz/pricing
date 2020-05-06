package com.mkaszynski.tdd.pricing;

import lombok.Value;

@Value
public class Product {
    private final String name;
    private final int price;
    private final Type type;

    boolean isFood() {
        return getType() == Type.FOOD;
    }

    enum Type {
        FOOD, DRINK
    }
}
