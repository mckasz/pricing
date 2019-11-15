package com.mkaszynski.tdd.pricing;

import java.util.Objects;

class Product {
    private final String name;
    private final int quantity;
    private final int price;
    private final Type type;

    enum Type {
        LIQUID,
        FOOD;

    }
    public Product(String name, int quantity, int price, Type type) {
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
        return new Product(name, quantity, price, type);
    }

    Product freeProduct(int quantity) {
        return new Product(name, quantity, 0, type);
    }

    Product discountedProduct(int discountedPrice) {
        return new Product(name, quantity, discountedPrice, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                quantity == product.quantity &&
                Objects.equals(name, product.name) &&
                type == product.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, type);
    }
}
