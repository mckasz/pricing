package com.mkaszynski.tdd.pricing;

import lombok.Value;

@Value
class Product {
    private final String name;
    private final int price;
    private final SelectedProduct.Type type;

    SelectedProduct toSelectedProduct(int quantity) {
        return new SelectedProduct(getName(), getPrice(), quantity, getType());
    }
}
