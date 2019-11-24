package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.SummaryItem;
import com.mkaszynski.tdd.pricing.promotions.Discount;
import lombok.Value;

import static com.mkaszynski.tdd.pricing.SelectedProduct.Type.LIQUID;

@Value
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

    public boolean isLiquid() {
        return type == LIQUID;
    }

    public int quantity() {
        return quantity;
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

    SelectedProduct addQuantity(int otherQuantity) {
        return new SelectedProduct(name, price, quantity + otherQuantity, type);
    }

    SummaryItem toSummaryItem() {
        return new SummaryItem(getName(), getPrice(), quantity());
    }

    public enum Type {
        LIQUID,
        FOOD
    }
}
