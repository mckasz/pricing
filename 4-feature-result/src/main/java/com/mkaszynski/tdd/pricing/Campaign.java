package com.mkaszynski.tdd.pricing;

import java.util.List;

class Campaign {
    private List<String> productNames;
    private Promotion promotion;

    public Campaign(List<String> productNames, Promotion promotion) {
        this.productNames = productNames;
        this.promotion = promotion;
    }

    List<Product> applyPromotion(Product product) {
        return promotion.apply(product);
    }

    boolean appliesFor(Product product) {
        return productNames.contains(product.name());
    }
}
