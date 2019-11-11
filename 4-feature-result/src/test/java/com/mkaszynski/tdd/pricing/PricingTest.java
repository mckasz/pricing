package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.util.CamelCaseAndUnderscoresGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;

import static com.mkaszynski.tdd.pricing.HappyHours.Duration.DurationBuilder.from;
import static com.mkaszynski.tdd.pricing.Product.Type.LIQUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
            assertThat(products).containsOnly(new Product("beer", 2, 1000, LIQUID));
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

        private TimeProvider currentTime(String time) {
            return new TimeProvider() {
                @Override
                public LocalTime now() {
                    return LocalTime.parse(time);
                }
            };
        }
    }

    private class TestPromotion implements Promotion {
        @Override
        public List<Product> apply(Product product) {
            return newArrayList(product);
        }
    }
}