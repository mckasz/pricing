package com.mkaszynski.tdd.pricing;

import lombok.Value;

@Value
class Product {
    String name;
    int price;
    int quantity;

    Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product freeProduct(int quantity) {
        return new Product(name, 0, quantity);
    }

    public Product productOfQuantity(int quantity) {
        return new Product(name, price, quantity);
    }
}
