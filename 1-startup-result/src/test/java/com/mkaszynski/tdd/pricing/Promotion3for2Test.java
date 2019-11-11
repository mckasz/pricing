package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(CamelCaseAndUnderscoresGenerator.class)
class Promotion3for2Test {

    private final Promotion3for2 promotion = new Promotion3for2();

    @Test
    void for2products_priceDoesNotChange() {
        Product fullPriceMilk = fullPriceMilk(2);

        List<Product> result = promotion.apply(fullPriceMilk);

        assertThat(result).containsOnly(fullPriceMilk(2));
    }

    @Test
    void for3products_priceForTwoProductsDoesNotChange_thirdIsReducedToZero() {
        Product fullPriceMilk = fullPriceMilk(3);

        List<Product> result = promotion.apply(fullPriceMilk);

        assertThat(result).containsOnly(
                fullPriceMilk(2), freeMilk(1));
    }

    @Test
    void for6products_priceFor4ProductsDoesNotChange_twoOfThemIsReducedToZero() {
        Product fullPriceMilk = fullPriceMilk(6);

        List<Product> result = promotion.apply(fullPriceMilk);

        assertThat(result).containsOnly(
                fullPriceMilk(4), freeMilk(2));
    }

    @Test
    void for7products_priceFor5ProductsDoesNotChange_twoOfThemIsReducedToZero() {
        Product fullPriceMilk = fullPriceMilk(7);

        List<Product> result = promotion.apply(fullPriceMilk);

        assertThat(result).containsOnly(
                fullPriceMilk(5), freeMilk(2));
    }

    @Test
    void for11products_priceFor8ProductsDoesNotChange_3areFree() {
        Product fullPriceMilk = fullPriceMilk(11);

        List<Product> result = promotion.apply(fullPriceMilk);

        assertThat(result).containsOnly(
                fullPriceMilk(8), freeMilk(3));
    }

    private static Product freeMilk(int quantity) {
        return new Product("Milk", 0, quantity);
    }

    private static Product fullPriceMilk(int quantity) {
        return new Product("Milk", 220, quantity);
    }
}
