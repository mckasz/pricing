package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.SummaryItem;
import com.mkaszynski.tdd.pricing.promotions.Discount;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import static com.mkaszynski.tdd.pricing.Product.Type.LIQUID;

@EqualsAndHashCode
@ToString
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

    public String name() {
        return name;
    }

    public int quantity() {
        return quantity;
    }

    public boolean isLiquid() {
        return type == LIQUID;
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

    Product addQuantity(Product product) {
        return new Product(name, price, quantity + product.quantity, type);
    }

    SummaryItem toSummaryItem() {
        return new SummaryItem(name, price, quantity);
    }

    Key key() {
        return new Key(name, price);
    }

    @EqualsAndHashCode
    static class Key {
        // Accessed by map
        @SuppressWarnings("FieldCanBeLocal")
        private final String name;
        @SuppressWarnings("FieldCanBeLocal")
        private final int price;

        Key(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }

    public enum Type {
        LIQUID,
        FOOD;
    }
}
