package com.mkaszynski.tdd.pricing.promotions;

import com.mkaszynski.tdd.pricing.Discount;
import com.mkaszynski.tdd.pricing.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import static com.mkaszynski.tdd.pricing.promotions.HappyHour.HappyHourBuilder.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("Happy hours promotions should")
class HappyHoursPromotionTest {

    @Test
    @DisplayName("give 30% discount to LIQUIDS when in happy hours")
    void givesDiscountToLiquids() {
        HappyHoursPromotion promotion = happyHoursPromotion(currentTime("14:00"));

        List<Product> products = promotion.apply(beer());

        assertThat(products).containsOnly(discountedBeer());
    }

    @Test
    @DisplayName("not give discount to FOOD products when in happy hours")
    void foodNotDiscounted() {
        HappyHoursPromotion promotion = happyHoursPromotion(currentTime("14:00"));

        List<Product> products = promotion.apply(butter());

        assertThat(products).containsOnly(butter());
    }

    @Test
    @DisplayName("not give discount to LIQUIDS products when outside happy hours")
    void outsideHappyHoursLiquidNotDiscounted() {
        HappyHoursPromotion promotion = happyHoursPromotion(currentTime("16:00"));

        List<Product> products = promotion.apply(beer());

        assertThat(products).containsOnly(beer());
    }

    @Test
    @DisplayName("not give discount to FOOD products when outside happy hours")
    void outsideHappyHoursFoodNotDiscounted() {
        HappyHoursPromotion promotion = happyHoursPromotion(currentTime("16:00"));

        List<Product> products = promotion.apply(butter());

        assertThat(products).containsOnly(butter());
    }

    @Test
    @DisplayName("Throw exception when happy hours start is after end")
    void invalidHappyHour() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new HappyHoursPromotion(new Discount(30), from("16:00").to("15:00"), currentTime("16:00")))
                .withMessage("Happy hour start time 16:00 is after end time 15:00");
    }

    @Test
    @DisplayName("Throw exception when happy hours start is in invalid format")
    void invalidHappyHourParse() {
        assertThatExceptionOfType(DateTimeParseException.class)
                .isThrownBy(() -> new HappyHoursPromotion(new Discount(30), from("16:00zsa").to("15:00"), currentTime("16:00")))
                .withMessage("Text '16:00zsa' could not be parsed, unparsed text found at index 5");
    }

    @Test
    @DisplayName("Throw exception discount is higher than 100")
    void discountTooHigh() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new HappyHoursPromotion(new Discount(120), from("12:00zsa").to("14:00"), currentTime("16:00")))
                .withMessage("Discount cannot be higher than 100. Given: 120");
    }

    private static HappyHoursPromotion happyHoursPromotion(TimeProvider timeProvider) {
        return new HappyHoursPromotion(new Discount(30), from("12:00").to("15:00"), timeProvider);
    }

    private static TimeProvider currentTime(String time) {
        return () -> LocalTime.parse(time);
    }

    private static Product beer() {
        return new Product("butter", 1000, 2, Product.Type.LIQUID);
    }

    private static Product discountedBeer() {
        return new Product("butter", 700, 2, Product.Type.LIQUID);
    }

    private static Product butter() {
        return new Product("butter", 600, 2, Product.Type.FOOD);
    }
}