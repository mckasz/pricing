package com.mkaszynski.tdd.pricing;

import lombok.Value;

import static com.mkaszynski.tdd.pricing.Product.Type.LIQUID;

@Value
class Product {
    private final String name;
    private final int price;
    private final int quantity;
    private final Type type;

    Product applyDiscount(Discount discount) {
        int discountedPrice = (int) (price() * discount.multiplier());
        return new Product(name, discountedPrice, quantity, type);
    }

    boolean isLiquid() {
        return type() == LIQUID;
    }

    enum Type {
        LIQUID,
        FOOD;
    }

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

    Product applyDiscount(int discountedPrice) {
        return new Product(name, discountedPrice, quantity, type);
    }
}
