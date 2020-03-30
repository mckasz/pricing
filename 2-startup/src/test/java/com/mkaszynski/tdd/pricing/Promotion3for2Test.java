package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Promotion3for2Test {

    private final Promotion3for2 promotion = new Promotion3for2();

    @Test
    void shouldReturnProductWithSamePrice_whenOnlyOneProductGiven() {
        List<Product> result = promotion.apply(product(1));

        assertThat(result).containsOnly(product(1));
    }

    @Test
    void shouldReturnTwoProductsWithSamePrice_whenTwoProductsGiven() {
        List<Product> result = promotion.apply(product(2));

        assertThat(result).containsOnly(product(2));
    }

    @Test
    void shouldReturn2ProductWithSamePriceAnd1ForFree_whenThreeProductsGiven() {
        List<Product> result = promotion.apply(product(3));

        assertThat(result).containsOnly(product(2), freeProduct(1));
    }

    @Test
    void shouldReturn3ProductWithSamePriceAnd1ForFree_when4ProductsGiven() {
        List<Product> result = promotion.apply(product(4));

        assertThat(result).containsOnly(product(3), freeProduct(1));
    }
    @Test
    void shouldReturn4ProductWithSamePriceAnd2ForFree_when6ProductsGiven() {
        List<Product> result = promotion.apply(product(6));

        assertThat(result).containsOnly(product(4), freeProduct(2));
    }

    private Product product(int quantity) {
        return new Product("ProductName", 220, quantity);
    }

    private Product freeProduct(int quantity) {
        return new Product("ProductName", 0, quantity);
    }
}