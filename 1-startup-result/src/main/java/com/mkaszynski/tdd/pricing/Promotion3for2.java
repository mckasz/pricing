package com.mkaszynski.tdd.pricing;

import java.util.List;

class Promotion3for2 {
    List<Product> apply(Product product) {
        Products productsAfterPromotion = new Products();
        productsAfterPromotion.add(product.samePrice(numberOfSamePriceProducts(product.quantity())));
        productsAfterPromotion.add(product.freeProduct(numberOfFreeProducts(product.quantity())));
        return productsAfterPromotion.asList();
    }

    private int numberOfSamePriceProducts(int quantity) {
        int remainder = quantity % 3;
        int fullPrice = (quantity / 3) * 2;
        return fullPrice + remainder;
    }

    private int numberOfFreeProducts(int quantity) {
        return quantity / 3;
    }
}
