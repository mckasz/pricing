package com.mkaszynski.tdd.pricing;

import lombok.Value;

import static com.mkaszynski.tdd.pricing.Product.Type.LIQUID;

@Value
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

    boolean isLiquid() {
        return type == LIQUID;
    }

    int quantity() {
        return quantity;
    }

    Product samePrice(int quantity) {
        return new Product(name, price, quantity, type);
    }

    Product freeProduct(int quantity) {
        return new Product(name, 0, quantity, type);
    }

    Product applyDiscount(Discount discount) {
        return new Product(name, calculateDiscount(discount), quantity, type);
    }

    private int calculateDiscount(Discount discount) {
        return (int) (price * discount.multiplier());
    }

    enum Type {
        LIQUID,
        FOOD
    }
}
