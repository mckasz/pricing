package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class Pricing {

    private final Campaign campaign;

    Pricing(Campaign campaign) {
        this.campaign = campaign;
    }

    List<Product> reprice(List<Product> products) {
        List<Product> repricedProducts = new ArrayList<>();
        for (Product product : products) {
            if (campaign.appliesFor(product)) {
                repricedProducts.addAll(campaign.applyPromotion(product));
            } else {
                repricedProducts.addAll(products);
            }
        }
        return repricedProducts;
    }
}
