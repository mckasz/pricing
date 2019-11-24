package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.promotions.Promotion;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor public
class Campaign {
    private final String productName;
    private final Promotion promotion;

    boolean appliesFor(SelectedProduct product) {
        return this.productName.equals(product.name());
    }

    List<SelectedProduct> applyPromotion(SelectedProduct product) {
        return this.promotion.apply(product);
    }

    public static Campaign emptyCampaign() {
        return new Campaign("", Collections::singletonList);
    }
}
