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
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    String name() {
        return name;
    }

    boolean isLiquid() {
        return type == LIQUID;
    }

    Product samePrice(int quantity) {
        return new Product(name, quantity, price, type);
    }

    Product freeProduct(int quantity) {
        return new Product(name, quantity, 0, type);
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
