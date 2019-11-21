package com.mkaszynski.tdd.pricing.promotions;

import com.mkaszynski.tdd.pricing.Product;

import java.time.LocalTime;
import java.util.List;

import static java.util.Collections.singletonList;

class HappyHoursPromotion implements Promotion {
    private final Discount discount;
    private final HappyHour happyHour;
    private final TimeProvider timeProvider;

    HappyHoursPromotion(Discount discount, HappyHour happyHour, TimeProvider timeProvider) {
        this.discount = discount;
        this.happyHour = happyHour;
        this.timeProvider = timeProvider;
    }

    @Override
    public List<Product> apply(Product product) {
        if (product.isLiquid() && isIn(happyHour)) {
            return singletonList(product.applyDiscount(discount));
        }
        return singletonList(product);
    }

    private boolean isIn(HappyHour happyHour) {
        LocalTime now = timeProvider.now();
        return now.isAfter(happyHour.start()) && now.isBefore(happyHour.end());
    }
}
