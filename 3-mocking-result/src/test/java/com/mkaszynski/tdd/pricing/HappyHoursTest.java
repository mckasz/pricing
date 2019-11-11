package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static com.mkaszynski.tdd.pricing.HappyHours.Duration.DurationBuilder.from;
import static com.mkaszynski.tdd.pricing.Product.Type.LIQUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HappyHoursTest {
    private static TimeProvider currentTime(String time) {
        return new TimeProvider() {
            @Override
            public LocalTime now() {
                return LocalTime.parse(time);
            }
        };
    }

    @Test
    void whenInHappyHoursLiquidPricesHave30PercentDiscount() {
        // given
        HappyHours happyHours = new HappyHours(from("12:00").to("15:00"), new Discount(30), currentTime("12:15"));
        Product product = new Product("beer", 2, 1000, LIQUID);

        // when
        List<Product> products = happyHours.apply(product);

        // then
        assertThat(products).containsOnly(new Product("beer", 2, 700, LIQUID));
    }

    @Test
    void whenOutsideHappyHoursLiquidPricesAreNotReduced() {
        // given
        HappyHours happyHours = new HappyHours(from("12:00").to("15:00"), new Discount(30), currentTime("15:15"));
        Product product = new Product("beer", 2, 1000, LIQUID);

        // when
        List<Product> products = happyHours.apply(product);

        // then
        assertThat(products).containsOnly(new Product("beer", 2, 700, LIQUID));
    }

    @Test
    void startTimeThatIsAfterEndTimeCausesException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new HappyHours(from("16:00").to("15:00"), new Discount(30), currentTime("15:15")));
        assertThat(exception).hasMessage("Start [16:00] time cannot be after end time [15:00]");
    }

    @Test
    void discountHigherThan100PercentCausesException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new HappyHours(from("12:00").to("15:00"), new Discount(101), currentTime("15:15")));
        assertThat(exception).hasMessage("Too high price reduction: 101.0%");
    }
}