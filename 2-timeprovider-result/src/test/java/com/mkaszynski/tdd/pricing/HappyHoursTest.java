package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static com.mkaszynski.tdd.pricing.HappyHours.Duration.DurationBuilder.from;
import static com.mkaszynski.tdd.pricing.Product.Type.FOOD;
import static com.mkaszynski.tdd.pricing.Product.Type.LIQUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HappyHoursTest {
    @Test
    void whenInHappyHoursLiquidPricesHave30PercentDiscount() {
        // given
        HappyHours happyHours = new HappyHours(from("12:00").to("15:00"), Discount30Percent(), currentTime("12:15"));

        // when
        List<Product> products = happyHours.apply(fullPriceBeer());

        // then
        assertThat(products).containsOnly(reducedPriceBeer());
    }

    @Test
    void whenInHappyHoursFoodPricesAreNotReduced() {
        // given
        HappyHours happyHours = new HappyHours(from("12:00").to("15:00"), Discount30Percent(), currentTime("12:15"));

        // when
        List<Product> products = happyHours.apply(bananas());

        // then
        assertThat(products).containsOnly(bananas());
    }

    @Test
    void whenOutsideHappyHoursLiquidPricesAreNotReduced() {
        // given
        HappyHours happyHours = new HappyHours(from("12:00").to("15:00"), Discount30Percent(), currentTime("15:15"));

        // when
        List<Product> products = happyHours.apply(fullPriceBeer());

        // then
        assertThat(products).containsOnly(fullPriceBeer());
    }

    @Test
    void startTimeThatIsAfterEndTimeCausesException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new HappyHours(from("16:00").to("15:00"), Discount30Percent(), currentTime("15:15")));
        assertThat(exception).hasMessage("Start [16:00] time cannot be after end time [15:00]");
    }

    @Test
    void discountHigherThan100PercentCausesException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new HappyHours(from("12:00").to("15:00"), new Discount(101), currentTime("15:15")));
        assertThat(exception).hasMessage("Too high price reduction: 101.0%");
    }

    private static TimeProvider currentTime(String time) {
        return new TimeProvider() {
            @Override
            public LocalTime now() {
                return LocalTime.parse(time);
            }
        };
    }

    private Product bananas() {
        return new Product("bananas", 500, 2, FOOD);
    }

    private Product fullPriceBeer() {
        return new Product("beer", 1000, 2, LIQUID);
    }

    private Product reducedPriceBeer() {
        return new Product("beer", 700, 2, LIQUID);
    }

    private Discount Discount30Percent() {
        return new Discount(30);
    }
}