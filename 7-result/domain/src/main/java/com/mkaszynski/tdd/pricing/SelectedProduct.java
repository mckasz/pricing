package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.SummaryItem;
import com.mkaszynski.tdd.pricing.promotions.Discount;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class SelectedProduct {
    private final String name;
    private final int price;
    private final int quantity;
    private final Type type;

    public SelectedProduct(String name, int price, int quantity, Type type) {
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
        return type == Type.LIQUID;
    }

    public SelectedProduct samePrice(int quantity) {
        return new SelectedProduct(name, price, quantity, type);
    }

    public SelectedProduct freeProduct(int quantity) {
        return new SelectedProduct(name, 0, quantity, type);
    }

    public SelectedProduct applyDiscount(Discount discount) {
        return new SelectedProduct(name, calculateDiscount(discount), quantity, type);
    }

    private int calculateDiscount(Discount discount) {
        return (int) (price * discount.multiplier());
    }

    SelectedProduct addQuantity(SelectedProduct product) {
        return new SelectedProduct(name, price, quantity + product.quantity, type);
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
