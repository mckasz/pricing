package com.mkaszynski.tdd.pricing.promotions;

import com.mkaszynski.tdd.pricing.SelectedProduct;
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
        SelectedProduct product = fullPriceMilk(1);

        // act
        List<SelectedProduct> result = promotion.apply(product);

        // assert
        assertThat(result).containsOnly(fullPriceMilk(1));
    }

    @Test
    @DisplayName("should return product with same price for two products")
    void twoProducts() {
        // arrange
        SelectedProduct product = fullPriceMilk(2);

        // act
        List<SelectedProduct> result = promotion.apply(product);

        // assert
        assertThat(result).containsOnly(fullPriceMilk(2));
    }

    @Test
    @DisplayName("should return one free product with for three same products")
    void threeProducts() {
        // arrange
        SelectedProduct product = fullPriceMilk(3);

        // act
        List<SelectedProduct> result = promotion.apply(product);

        // assert
        assertThat(result).containsOnly(fullPriceMilk(2), freeMilk(1));
    }

    @Test
    @DisplayName("should return one free product with for four same products")
    void fourProducts() {
        // arrange
        SelectedProduct product = fullPriceMilk(4);

        // act
        List<SelectedProduct> result = promotion.apply(product);

        // assert
        assertThat(result).containsOnly(fullPriceMilk(3), freeMilk(1));
    }

    @Test
    @DisplayName("should return one free product with for five same products")
    void fiveProducts() {
        // arrange
        SelectedProduct product = fullPriceMilk(5);

        // act
        List<SelectedProduct> result = promotion.apply(product);

        // assert
        assertThat(result).containsOnly(fullPriceMilk(4), freeMilk(1));
    }

    @Test
    @DisplayName("should return two free products with for six same products")
    void sixProducts() {
        // arrange
        SelectedProduct product = fullPriceMilk(6);

        // act
        List<SelectedProduct> result = promotion.apply(product);

        // assert
        assertThat(result).containsOnly(fullPriceMilk(4), freeMilk(2));
    }

    private static SelectedProduct fullPriceMilk(int quantity) {
        return new SelectedProduct("milk", 220, quantity, SelectedProduct.Type.FOOD);
    }

    private static SelectedProduct freeMilk(int quantity) {
        return new SelectedProduct("milk", 0, quantity, SelectedProduct.Type.FOOD);
    }
}