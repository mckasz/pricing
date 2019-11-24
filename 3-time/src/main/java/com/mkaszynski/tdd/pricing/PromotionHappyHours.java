package com.mkaszynski.tdd.pricing;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

class PromotionHappyHours {
    private TimeProvider timeProvider;

    PromotionHappyHours(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    List<Product> apply(Product product) {
        if (timeProvider.now().isAfter(LocalTime.parse("12:00")) && timeProvider.now().isBefore(LocalTime.parse("14:00"))) {
            return Collections.singletonList(product.discountedProduct());
        }

        return Collections.singletonList(product);
    }

}
