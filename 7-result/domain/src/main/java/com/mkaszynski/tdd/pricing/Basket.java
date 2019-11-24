package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class Basket {
    private final Long id;
    private final SelectedProducts products = new SelectedProducts();
    private final Campaign campaign;

    public Basket(Long id, List<SelectedProduct> products, Campaign campaign) {
        this.id = id;
        this.campaign = campaign;
        for (SelectedProduct product : products) {
            this.products.add(product);
        }
    }

    public Long id() {
        return id;
    }

    void add(SelectedProduct product) {
        products.add(product);
    }

    List<SelectedProduct> products() {
        List<SelectedProduct> productsAfterPromotion = new ArrayList<>();
        for (SelectedProduct product : products) {
            if (campaign.appliesFor(product)) {
                productsAfterPromotion.addAll(campaign.applyPromotion(product));
            } else {
                productsAfterPromotion.add(product);
            }
        }
        return productsAfterPromotion;
    }
}
