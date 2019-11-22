package com.mkaszynski.tdd.pricing;

public class Discount {
    private final double discount;

    public Discount(double discount) {
        this.discount = discount;
    }

    double multiplier() {
        return (100 - discount) / 100;
    }
}
