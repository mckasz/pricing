package com.mkaszynski.tdd.pricing;

import lombok.Value;

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

    int price() {
        return price;
    }

    String name() {
        return name;
    }

    boolean isLiquid() {
        return type() == Type.LIQUID;
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

    Product discountedProduct(Discount discount) {
        return new Product(name, getDiscountedPrice(discount), quantity, type);
    }

    private int getDiscountedPrice(Discount discount) {
        return (int) (price * discount.multiplier());
    }

    enum Type {
        LIQUID,
        FOOD
    }
}
