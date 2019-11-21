package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.promotions.Discount;
import lombok.Value;

import static com.mkaszynski.tdd.pricing.Product.Type.LIQUID;

@Value
public class Product {
    private final String name;
    private final int price;
    private final int quantity;
    private final Type type;

    public Product(String name, int price, int quantity, Type type) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }

    public boolean isLiquid() {
        return type == LIQUID;
    }

    public int quantity() {
        return quantity;
    }

    public Product samePrice(int quantity) {
        return new Product(name, price, quantity, type);
    }

    public Product freeProduct(int quantity) {
        return new Product(name, 0, quantity, type);
    }

    public Product applyDiscount(Discount discount) {
        return new Product(name, calculateDiscount(discount), quantity, type);
    }

    private int calculateDiscount(Discount discount) {
        return (int) (price * discount.multiplier());
    }

    Product addQuantity(int otherQuantity) {
        return new Product(name, price, quantity + otherQuantity, type);
    }

    SummaryItem toSummaryItem() {
        return new SummaryItem(getName(), getPrice(), getQuantity());
    }

    public enum Type {
        LIQUID,
        FOOD
    }
}
