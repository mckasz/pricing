package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class Basket {
    private final Products products = new Products();
    private final Campaign campaign;

    Basket(List<Product> products, Campaign campaign) {
        this.campaign = campaign;
        for (Product product : products) {
            this.products.add(product);
        }
    }

    void add(Product product) {
        products.add(product);
    }

    List<Product> products() {
        List<Product> productsAfterPromotion = new ArrayList<>();
        for (Product product : products) {
            if (campaign.appliesFor(product)) {
                productsAfterPromotion.addAll(campaign.applyPromotion(product));
            } else {
                productsAfterPromotion.add(product);
            }
        }
        return productsAfterPromotion;
    }
}
