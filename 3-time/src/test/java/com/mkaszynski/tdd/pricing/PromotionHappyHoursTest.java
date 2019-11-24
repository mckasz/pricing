package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PromotionHappyHoursTest {

    @Test
    @DisplayName("give 30% discount when it is Happy Hour")
    void givesDiscountToLiquids() {
        PromotionHappyHours promotion = new PromotionHappyHours(currentTime("13:00"));

        List<Product> products = promotion.apply(beer());

        assertThat(products).containsOnly(discountedBeer());
    }

    @Test
    @DisplayName("not give 30% discount when it is not Happy Hour")
    void notGiveDiscount() {
        PromotionHappyHours promotion = new PromotionHappyHours(currentTime("16:00"));

        List<Product> products = promotion.apply(beer());

        assertThat(products).containsOnly(beer());
    }

    private TimeProvider currentTime(String time) {
        return () -> LocalTime.parse(time);
    }

    private Product beer() {
        return new Product("Beer", 1000, 1, Product.Type.LIQUID);
    }

    private Product discountedBeer() {
        return new Product("Beer", 700, 1, Product.Type.LIQUID);
    }


}