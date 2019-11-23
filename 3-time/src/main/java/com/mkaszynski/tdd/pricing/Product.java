package com.mkaszynski.tdd.pricing;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
class Product {
    private final String name;
    private final int price;
    private final int quantity;
    private final Type type;

    Product(String name, int price, int quantity, Type type) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }

    int price() {
        return price;
    }

    String name() {
        return name;
    }

    int quantity() {
        return quantity;
    }

    Type type() {
        return type;
    }

    Product samePrice(int quantity) {
        return new Product(name, price, quantity, type);
    }

    Product freeProduct(int quantity) {
        return new Product(name, 0, quantity, type);
    }

    enum Type {
        LIQUID,
        FOOD
    }
}
