package com.mkaszynski.tdd.pricing;

public class Discount {
    private final int valueInPercent;

    public Discount(int valueInPercent) {
        this.valueInPercent = valueInPercent;
    }

    double multiplier() {
        return ((double) (100 - valueInPercent)) / 100;
    }
}
