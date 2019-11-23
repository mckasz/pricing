package com.mkaszynski.tdd.pricing.promotions;

import com.mkaszynski.tdd.pricing.SelectedProduct;
import com.mkaszynski.tdd.pricing.SelectedProducts;

import java.util.List;

public class Promotion3for2 implements Promotion {

    @Override
    public List<SelectedProduct> apply(SelectedProduct product) {
        SelectedProducts productsAfterPromotion = new SelectedProducts();
        productsAfterPromotion.add(product.samePrice(numberOfSamePriceProducts(product.quantity())));
        productsAfterPromotion.add(product.freeProduct(numberOfFreeProducts(product.quantity())));
        return productsAfterPromotion.asList();
    }

    private int numberOfSamePriceProducts(int quantity) {
        int numberOfFreeProducts = numberOfFreeProducts(quantity);
        return quantity - numberOfFreeProducts;
    }

    private int numberOfFreeProducts(int quantity) {
        return quantity / 3;
    }
}
