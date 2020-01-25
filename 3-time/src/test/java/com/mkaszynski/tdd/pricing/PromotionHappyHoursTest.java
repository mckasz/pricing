package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PromotionHappyHoursTest {

    @DisplayName("Should not apply promotion for FOOD")
    @Test
    void food() {
        PromotionHappyHours promotion = promotion();

        List<Product> result = promotion.apply(butter());

        assertThat(result).containsOnly(butter());
    }

    @DisplayName("Should apply promotion for LIQUID")
    @Test
    void liquid() {
        PromotionHappyHours promotion = promotion();

        List<Product> result = promotion.apply(beer());

        assertThat(result).containsOnly(discountedBeer());
    }

    private PromotionHappyHours promotion() {
        return new PromotionHappyHours(new Discount(30));
    }

    private static Product butter() {
        return new Product("butter", 220, 1, Product.Type.FOOD);
    }

    private static Product beer() {
        return new Product("beer", 1000, 1, Product.Type.LIQUID);
    }

    private static Product discountedBeer() {
        return new Product("beer", 700, 1, Product.Type.LIQUID);
    }
}