package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.promotions.Promotion;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
class Campaign {
    private final String productName;
    private final Promotion promotion;

    boolean appliesFor(SelectedProduct product) {
        return this.productName.equals(product.getName());
    }

    List<SelectedProduct> applyPromotion(SelectedProduct product) {
        return this.promotion.apply(product);
    }

    static Campaign emptyCampaign() {
        return new Campaign("", Collections::singletonList);
    }
}
