package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static com.mkaszynski.tdd.pricing.Product.Type.FOOD;
import static com.mkaszynski.tdd.pricing.TestTimeProvider.nowIs;
import static org.assertj.core.api.Assertions.assertThat;

class PromotionHappyHoursTest {

    @DisplayName("should return same product when outside of happy hours")
    @Test
    void fullPriceProduct() {
        PromotionHappyHours promotion = new PromotionHappyHours();
        LocalTime start = LocalTime.parse("12:00");
        LocalTime end = LocalTime.parse("14:00");
        Product product = product();

        List<Product> result = promotion.apply(product, start, end, new Discount(30), nowIs("15:00"));

        assertThat(result).containsOnly(product());
    }

    @DisplayName("Should return discounted product, when in happy hours")
    @Test
    void discountedProduct() {
        PromotionHappyHours promotion = new PromotionHappyHours();
        LocalTime start = LocalTime.parse("12:00");
        LocalTime end = LocalTime.parse("14:00");
        Product product = product();

        List<Product> result = promotion.apply(product, start, end, new Discount(30), nowIs("13:00"));

        assertThat(result).containsOnly(discoutnedProduct());
    }

    private Product product() {
        return new Product("ProductName", 10, 1, FOOD);
    }

    private Product discoutnedProduct() {
        return new Product("ProductName", 7, 1, FOOD);
    }

}
