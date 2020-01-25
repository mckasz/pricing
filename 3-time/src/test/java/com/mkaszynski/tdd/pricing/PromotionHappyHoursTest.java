package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PromotionHappyHoursTest {

    @DisplayName("Should not apply promotion for FOOD and it is happy hour")
    @Test
    void food() {
        PromotionHappyHours promotion = promotion("12:30");

        List<Product> result = promotion.apply(butter());

        assertThat(result).containsOnly(butter());
    }

    @DisplayName("Should not apply promotion when it is outside happy hour")
    @Test
    void outsideHappyHours() {
        PromotionHappyHours promotion = promotion("15:30");

        List<Product> result = promotion.apply(beer());

        assertThat(result).containsOnly(beer());
    }

    @DisplayName("Should apply promotion for LIQUID when it is HappyHour")
    @Test
    void liquidAndHappyHour() {
        PromotionHappyHours promotion = promotion("12:30");

        List<Product> result = promotion.apply(beer());

        assertThat(result).containsOnly(discountedBeer());
    }

    @DisplayName("Should not apply promotion for FOOD when it is outside HappyHour")
    @Test
    void foodAndNotHappyHour() {
        PromotionHappyHours promotion = promotion("15:30");

        List<Product> result = promotion.apply(butter());

        assertThat(result).containsOnly(butter());
    }

    private PromotionHappyHours promotion(final String currentTime) {
        return new PromotionHappyHours(new Discount(30), new TimeProvider() {
            @Override
            public LocalTime now() {
                return LocalTime.parse(currentTime);
            }
        });
    }

    private static Product butter() {
        return new Product("butter", 1000, 1, Product.Type.FOOD);
    }

    private static Product discountedButter() {
        return new Product("butter", 700, 1, Product.Type.FOOD);
    }

    private static Product beer() {
        return new Product("beer", 1000, 1, Product.Type.LIQUID);
    }

    private static Product discountedBeer() {
        return new Product("beer", 700, 1, Product.Type.LIQUID);
    }
}