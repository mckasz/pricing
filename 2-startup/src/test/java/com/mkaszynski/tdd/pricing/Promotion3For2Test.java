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

    @DisplayName("Should return two product with same price and one discounted when product quantity is equal 3")
    @Test
    void threeProducts() {
        List<Product> products = promotion.apply(butter(3));

        assertThat(products).contains(butter(2), discountedButter(1));
    }

    @DisplayName("Should return 3 products with same price and 1 discounted when product quantity is equal 4")
    @Test
    void fourProducts() {
        List<Product> products = promotion.apply(butter(4));

        assertThat(products).contains(butter(3), discountedButter(1));
    }

    @DisplayName("Should return 4 products with same price and 1 discounted when product quantity is equal 5")
    @Test
    void fiveProducts() {
        List<Product> products = promotion.apply(butter(5));

        assertThat(products).contains(butter(4), discountedButter(1));
    }

    @DisplayName("Should return 4 products with same price and 2 discounted when product quantity is equal 6")
    @Test
    void sixProducts() {
        List<Product> products = promotion.apply(butter(6));

        assertThat(products).contains(butter(4), discountedButter(2));
    }

    private Product discountedButter(int quantity) {
        return new Product("butter", 0, quantity);
    }

    private static Product butter(int quantity) {
        return new Product("butter", 220, quantity);
    }
}