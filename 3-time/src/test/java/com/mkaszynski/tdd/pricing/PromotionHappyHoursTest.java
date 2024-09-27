package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mkaszynski.tdd.pricing.Duration.DurationBuilder.from;
import static com.mkaszynski.tdd.pricing.Product.Type.FOOD;
import static com.mkaszynski.tdd.pricing.TestTimeProvider.nowIs;
import static org.assertj.core.api.Assertions.assertThat;

class PromotionHappyHoursTest {

    @DisplayName("should return same product when outside of happy hours")
    @Test
    void fullPriceProduct() {
        PromotionHappyHours promotion = new PromotionHappyHours();

        List<Product> result = promotion.apply(product(),
                                               from("12:00").to("14:00").nowIs(nowIs("15:00")),
                                               new Discount(30));

        assertThat(result).containsOnly(product());
    }

    @DisplayName("Should return discounted product, when in happy hours")
    @Test
    void discountedProductTest() {
        PromotionHappyHours promotion = new PromotionHappyHours();

        List<Product> result = promotion.apply(product(),
                                               from("12:00").to("14:00").nowIs(nowIs("13:00")),
                                               new Discount(30));

        assertThat(result).containsOnly(discountedProduct());
    }

    private Product product() {
        return new Product("ProductName", 10, 1, FOOD);
    }

    private Product discountedProduct() {
        return new Product("ProductName", 7, 1, FOOD);
    }

}
