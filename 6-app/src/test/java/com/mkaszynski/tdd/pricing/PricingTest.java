package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.util.CamelCaseAndUnderscoresGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(CamelCaseAndUnderscoresGenerator.class)
class PricingTest {

    @Spy
    private Promotion promotion = new TestPromotion();

    @Test
    void appliesPromotionsToProductsDefinedInCampaign() {
        // given
        Campaign bananasCampaign = new Campaign(newArrayList("bananas"), promotion);
        Pricing pricing = new Pricing(bananasCampaign);
        List<Product> products = newArrayList(bananas());

        // when
        List<Product> repricedProducts = pricing.reprice(products);

        // then
        verify(promotion).apply(bananas());
        assertThat(repricedProducts).containsOnly(bananas());
    }

    @Test
    void doesNotApplyPromotionsToProductsThatAreNotDefinedInCampaign() {
        // given
        Campaign orangesCampaign = new Campaign(newArrayList("oranges"), promotion);
        Pricing pricing = new Pricing(orangesCampaign);
        List<Product> products = newArrayList(bananas());

        // when
        List<Product> repricedProducts = pricing.reprice(products);

        // then
        verify(promotion, never()).apply(bananas());
        assertThat(repricedProducts).containsOnly(bananas());
    }

    @Test
    void returnsProductAfterPromotion() {
        // given
        List<Product> productsAfterPromotion = newArrayList(bananasWithDiscount());
        when(promotion.apply(any())).thenReturn(productsAfterPromotion);
        Campaign bananasCampaign = new Campaign(newArrayList("bananas"), promotion);
        Pricing pricing = new Pricing(bananasCampaign);
        List<Product> products = newArrayList(bananas());

        // when
        List<Product> repricedProducts = pricing.reprice(products);

        // then
        assertThat(repricedProducts).containsOnly(bananasWithDiscount());
    }

    private Product bananas() {
        return bananas(200);
    }

    private Product bananasWithDiscount() {
        return bananas(180);
    }

    private Product bananas(int price) {
        return new Product("bananas", 5, price, Product.Type.FOOD);
    }

    @Nested
    class HappyHoursTest {
        @DisplayName("give 30% discount to LIQUIDS when in happy hours")
        @Test
        void givesDiscountToLiquids() {
            HappyHoursPromotion promotion = happyHoursPromotion(currentTime("14:00"));

            List<Product> products = promotion.apply(beer());

            assertThat(products).containsOnly(discountedBeer());
        }

        @DisplayName("not give discount to FOOD products when in happy hours")
        @Test
        void foodNotDiscounted() {
            HappyHoursPromotion promotion = happyHoursPromotion(currentTime("14:00"));

            List<Product> products = promotion.apply(butter());

            assertThat(products).containsOnly(butter());
        }

        @DisplayName("not give discount to LIQUIDS products when outside happy hours")
        @Test
        void outsideHappyHoursLiquidNotDiscounted() {
            HappyHoursPromotion promotion = happyHoursPromotion(currentTime("16:00"));

            List<Product> products = promotion.apply(beer());

            assertThat(products).containsOnly(beer());
        }

        @DisplayName("not give discount to FOOD products when outside happy hours")
        @Test
        void outsideHappyHoursFoodNotDiscounted() {
            HappyHoursPromotion promotion = happyHoursPromotion(currentTime("16:00"));

            List<Product> products = promotion.apply(butter());

            assertThat(products).containsOnly(butter());
        }

        @DisplayName("Throw exception when happy hours start is after end")
        @Test
        void invalidHappyHour() {
            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> new HappyHoursPromotion(new Discount(30),
                                                              HappyHour.HappyHourBuilder.from("16:00").to("15:00"),
                                                              currentTime("16:00")))
                    .withMessage("Happy hour start time 16:00 is after end time 15:00");
        }

        @DisplayName("Throw exception when happy hours start is in invalid format")
        @Test
        void invalidHappyHourParse() {
            assertThatExceptionOfType(DateTimeParseException.class)
                    .isThrownBy(() -> new HappyHoursPromotion(new Discount(30),
                                                              HappyHour.HappyHourBuilder.from("16:00zsa").to("15:00"),
                                                              currentTime("16:00")))
                    .withMessage("Text '16:00zsa' could not be parsed, unparsed text found at index 5");
        }

        @DisplayName("Throw exception discount is higher than 100")
        @Test
        void discountTooHigh() {
            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> new HappyHoursPromotion(new Discount(120), HappyHour.HappyHourBuilder
                            .from("12:00zsa").to("14:00"), currentTime("16:00")))
                    .withMessage("Discount cannot be higher than 100. Given: 120");
        }

        private HappyHoursPromotion happyHoursPromotion(TimeProvider timeProvider) {
            return new HappyHoursPromotion(new Discount(30), HappyHour.HappyHourBuilder.from("12:00").to("15:00"), timeProvider);
        }

        private TimeProvider currentTime(String time) {
            return () -> LocalTime.parse(time);
        }

        private Product beer() {
            return new Product("butter", 1000, 2, Product.Type.LIQUID);
        }

        private Product discountedBeer() {
            return new Product("butter", 700, 2, Product.Type.LIQUID);
        }

        private Product butter() {
            return new Product("butter", 600, 2, Product.Type.FOOD);
        }
    }

    private class TestPromotion implements Promotion {
        @Override
        public List<Product> apply(Product product) {
            return newArrayList(product);
        }
    }
}