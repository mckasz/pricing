package com.mkaszynski.tdd.pricing.promotions;

import com.mkaszynski.tdd.pricing.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Promotion3for2Test {

    private final Promotion3for2 promotion = new Promotion3for2();

    @Test
    @DisplayName("should return product with same price for one product")
    void oneProduct() {
        // arrange
        Product product = fullPriceMilk(1);

        // act
        List<Product> result = promotion.apply(product);

        // assert
        assertThat(result).containsOnly(fullPriceMilk(1));
    }

    @Test
    @DisplayName("should return product with same price for two products")
    void twoProducts() {
        // arrange
        Product product = fullPriceMilk(2);

        // act
        List<Product> result = promotion.apply(product);

        // assert
        assertThat(result).containsOnly(fullPriceMilk(2));
    }

    @Test
    @DisplayName("should return one free product with for three same products")
    void threeProducts() {
        // arrange
        Product product = fullPriceMilk(3);

        // act
        List<Product> result = promotion.apply(product);

        // assert
        assertThat(result).containsOnly(fullPriceMilk(2), freeMilk(1));
    }

    @Test
    @DisplayName("should return one free product with for four same products")
    void fourProducts() {
        // arrange
        Product product = fullPriceMilk(4);

        // act
        List<Product> result = promotion.apply(product);

        // assert
        assertThat(result).containsOnly(fullPriceMilk(3), freeMilk(1));
    }

    @Test
    @DisplayName("should return one free product with for five same products")
    void fiveProducts() {
        // arrange
        Product product = fullPriceMilk(5);

        // act
        List<Product> result = promotion.apply(product);

        // assert
        assertThat(result).containsOnly(fullPriceMilk(4), freeMilk(1));
    }

    @Test
    @DisplayName("should return two free products with for six same products")
    void sixProducts() {
        // arrange
        Product product = fullPriceMilk(6);

        // act
        List<Product> result = promotion.apply(product);

        // assert
        assertThat(result).containsOnly(fullPriceMilk(4), freeMilk(2));
    }

    private static Product fullPriceMilk(int quantity) {
        return new Product("milk", 220, quantity, Product.Type.FOOD);
    }

    private static Product freeMilk(int quantity) {
        return new Product("milk", 0, quantity, Product.Type.FOOD);
    }
}