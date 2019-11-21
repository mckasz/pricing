package com.mkaszynski.tdd.product;

import com.mkaszynski.tdd.pricing.promotions.Discount;
import lombok.Value;

@Value
public class Item {
    private final String name;
    private final int price;
    private final int quantity;

    public Item(String name, int price, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    Item applyDiscount(Discount discount) {
        return new Item(name, calculateDiscount(discount), quantity);
    }

    private int calculateDiscount(Discount discount) {
        return (int) (price * discount.multiplier());
    }
}
