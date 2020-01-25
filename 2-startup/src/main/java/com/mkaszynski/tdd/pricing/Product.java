package com.mkaszynski.tdd.pricing;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
class Product {
    private final String name;
    private final int price;
    private final int quantity;

    Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    int price() {
        return price;
    }

    String name() {
        return name + " ";
    }

    int quantity() {
        return quantity;
    }
}
