package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


class Promotion3for2Test {

    Promotion3for2 promotion = new Promotion3for2();

    @Test
    void shouldNotApply_forProductsUnder3Quantity() {
        List<Product> promotionResult = promotion.apply(product(1));

        assertThat(promotionResult).containsOnly(product(1));
    }

    @Test
    void shouldApplyPromotion_forProductWithQuantityOf3() {
        List<Product> promotionResult = promotion.apply(product(3));

        assertThat(promotionResult).containsOnly(product(2),
                                                 freeProduct(1));
    }

    @Test
    void shouldReturn2FreeProducts_forProductWithQuantityOf6() {
        List<Product> promotionResult = promotion.apply(product(6));

        assertThat(promotionResult).containsOnly(
                product(4),
                freeProduct(2)
        );
    }

    @Test
    void shouldReturn2FreeProductsAnd5FullPriceProducts_forProductWithQuantityOf7() {
        List<Product> promotionResult = promotion.apply(product(7));

        assertThat(promotionResult).containsOnly(
                product(5),
                freeProduct(2)
        );
    }

    private Product freeProduct(int quantity) {
        return new Product("Milk", 0, quantity);
    }

    private Product product(int quantity) {
        return new Product("Milk", 2, quantity);
    }
}