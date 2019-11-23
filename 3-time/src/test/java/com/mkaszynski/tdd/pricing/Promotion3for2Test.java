package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Promotion3for2Test {

    private final Promotion3for2 promotion = new Promotion3for2();

    @Test
    @DisplayName("should return product with same price for one product")
    void oneProduct() {
        Product product = fullPriceMilk(1);

        List<Product> result = promotion.apply(product);

        assertThat(result).containsOnly(fullPriceMilk(1));
    }

    @Test
    @DisplayName("should return product with same price for two products")
    void twoProducts() {
        Product product = fullPriceMilk(2);

        List<Product> result = promotion.apply(product);

        assertThat(result).containsOnly(fullPriceMilk(2));
    }

    @Test
    @DisplayName("should return one free product with for three same products")
    void threeProducts() {
        Product product = fullPriceMilk(3);

        List<Product> result = promotion.apply(product);

        assertThat(result).containsOnly(fullPriceMilk(2), freeMilk(1));
    }

    @Test
    @DisplayName("should return one free product with for four same products")
    void fourProducts() {
        Product product = fullPriceMilk(4);

        List<Product> result = promotion.apply(product);

        assertThat(result).containsOnly(fullPriceMilk(3), freeMilk(1));
    }

    @Test
    @DisplayName("should return one free product with for five same products")
    void fiveProducts() {
        Product product = fullPriceMilk(5);

        List<Product> result = promotion.apply(product);

        assertThat(result).containsOnly(fullPriceMilk(4), freeMilk(1));
    }

    @Test
    @DisplayName("should return two free products with for six same products")
    void sixProducts() {
        Product product = fullPriceMilk(6);

        List<Product> result = promotion.apply(product);

        assertThat(result).containsOnly(fullPriceMilk(4), freeMilk(2));
    }

    private Product fullPriceMilk(int quantity) {
        return new Product("milk", 220, quantity, Product.Type.FOOD);
    }

    private Product freeMilk(int quantity) {
        return new Product("milk", 0, quantity, Product.Type.FOOD);
    }
}