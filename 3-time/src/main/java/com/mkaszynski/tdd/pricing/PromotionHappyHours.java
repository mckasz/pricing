package com.mkaszynski.tdd.pricing;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

class PromotionHappyHours implements Promotion {

    private Discount discount;
    private TimeProvider timeProvider;

    public PromotionHappyHours(Discount discount, TimeProvider timeProvider) {
        this.discount = discount;
        this.timeProvider = timeProvider;
    }

    // Liquid products should be discounted when it is between 12 and 14
    @Override
    public List<Product> apply(Product product) {
        List<Product> result = new ArrayList<>();
        if (timeProvider.now().isAfter(LocalTime.parse("12:00"))
                && timeProvider.now().isBefore(LocalTime.parse("14:00"))
                && product.type() == Product.Type.LIQUID) {
            result.add(product.discountedProduct(discount));
        } else {
            result.add(product);
        }
        return result;
    }
}
