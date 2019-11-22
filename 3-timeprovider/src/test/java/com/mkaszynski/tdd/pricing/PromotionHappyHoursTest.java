package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static com.mkaszynski.tdd.pricing.HappyHour.HappyHourBuilder.from;
import static org.assertj.core.api.Assertions.assertThat;

class PromotionHappyHoursTest {

    @DisplayName("when product is not Liquid and it is not happy hours, return not discounted product")
    @Test
    @Disabled
    void falseFalse() {
        PromotionHappyHours promotion = new PromotionHappyHours(new Discount(30), currentTime("12:30"),
                                                                new HappyHour(LocalTime.parse("12:00"), LocalTime.parse("14:00")));

        List<Product> result = promotion.apply(butter());

        assertThat(result).containsOnly(butter());
    }

    @DisplayName("when product is Liquid return discounted Product")
    @Test
    void trueFalse() {
        PromotionHappyHours promotion = new PromotionHappyHours(new Discount(30),
                                                                currentTime("12:30"),
                                                                from("12:00").to("14:00"));

        List<Product> result = promotion.apply(beer());

        assertThat(result).containsOnly(discountedBeer());
    }

    @DisplayName("when it is happy hour return discounted Product")
    @Test
    void falseTrue() {
        PromotionHappyHours promotion = new PromotionHappyHours(new Discount(30), currentTime("12:30"),
                                                                new HappyHour(LocalTime.parse("12:00"), LocalTime.parse("14:00")));

        List<Product> result = promotion.apply(butter());

        assertThat(result).containsOnly(discountedButter());
    }

    private static TimeProvider currentTime(String text) {
        return () -> LocalTime.parse(text);
    }

    private static Product butter() {
        return new Product("Butter", 300, 1, Product.Type.FOOD);
    }

    private static Product discountedButter() {
        return new Product("Butter", 210, 1, Product.Type.FOOD);
    }

    private static Product discountedBeer() {
        return new Product("Beer", 700, 1, Product.Type.LIQUID);
    }
    private static Product beer() {
        return new Product("Beer", 1000, 1, Product.Type.LIQUID);
    }
}