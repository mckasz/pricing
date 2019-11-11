package com.mkaszynski.tdd.pricing;

import lombok.Value;

@Value
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
        return name;
    }

    Product samePrice(int quantity) {
        return new Product(name(), price, quantity);
    }

    Product freeProduct(int quantity) {
        return new Product(name(), 0, quantity);
    }

    int quantity() {
        return quantity;
    }
}
