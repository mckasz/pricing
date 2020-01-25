package com.mkaszynski.tdd.pricing;

public class Discount {
    private final double value;

    public Discount(double value) {
        this.value = value;
    }

    double multiplier() {
        return (100 - value) / 100;
    }
}
