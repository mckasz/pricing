package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class Promotion3For2Test {

    private final Promotion3For2 promotion = new Promotion3For2();

    @DisplayName("Should return single product with same price when one product in input")
    @Test
    void oneProduct() {
        List<Product> products = promotion.apply(butter(1));

        assertThat(products).contains(butter(1));
    }

    @DisplayName("Should return two product with same price when two product quantities")
    @Test
    void twoProducts() {
        List<Product> products = promotion.apply(butter(2));

        assertThat(products).contains(butter(2));
    }

    @DisplayName("Should return two product with same price and one discounted when three product quantity is equal 3")
    @Test
    void threeProducts() {
        List<Product> products = promotion.apply(butter(3));

        assertThat(products).contains(butter(2), discountedButter());
    }

    private Product discountedButter() {
        return new Product("butter", 0, 1);
    }

    private static Product butter(int quantity) {
        return new Product("butter", 220, quantity);
    }
}