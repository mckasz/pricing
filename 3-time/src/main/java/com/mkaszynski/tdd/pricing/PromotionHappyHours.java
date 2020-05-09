package com.mkaszynski.tdd.pricing;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

class PromotionHappyHours {
    List<Product> apply(Product product,
                        LocalTime start,
                        LocalTime end,
                        Discount discount,
                        TimeProvider timeProvider) {
        List<Product> result = new ArrayList<>();
        if (start.isBefore(timeProvider.now()) && end.isAfter(timeProvider.now())) {
            result.add(product.discountedProduct(discount));
        } else {
            result.add(product);
        }
        return result;
    }

}
