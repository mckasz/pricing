package com.mkaszynski.tdd.pricing;

import java.util.List;

import static java.util.Collections.singletonList;

class PromotionHappyHours {

    private final Discount discount;
    private final TimeProvider timeProvider;
    private final HappyHour happyHour;

    PromotionHappyHours(Discount discount, TimeProvider timeProvider, HappyHour happyHour) {
        this.discount = discount;
        this.timeProvider = timeProvider;
        this.happyHour = happyHour;
    }

    List<Product> apply(Product product) {
        if (product.isLiquid()) {
            return singletonList(product.discountedProduct(discount));
        }
        if (isIn(happyHour)) {
            return singletonList(product.discountedProduct(discount));
        }

        return singletonList(product);
    }

    private boolean isIn(HappyHour happyHour) {
        return timeProvider.now().isAfter(happyHour.getStart()) && timeProvider.now().isBefore(this.happyHour.getEnd());
    }

}
