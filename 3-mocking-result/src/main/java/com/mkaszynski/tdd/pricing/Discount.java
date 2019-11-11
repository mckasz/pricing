package com.mkaszynski.tdd.pricing;

class Discount {
    private final double value;

    Discount(double value) {
        if (value > 100) {
            throw new IllegalArgumentException(String.format("Too high price reduction: %s%%", value));
        }
        this.value = value;
    }

    double value() {
        return value;
    }

    double multiplier() {
        return (100 - value) / 100;
    }

    void validate() {
        if (value > 100) {
            throw new IllegalArgumentException(String.format("Too high price reduction: %s%%", value()));
        }
    }
}
