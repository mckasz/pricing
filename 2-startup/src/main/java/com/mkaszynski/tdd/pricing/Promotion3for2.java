package com.mkaszynski.tdd.pricing;

import java.util.List;

class Promotion3for2 {

    List<Product> apply(Product product) {
        return new Products()
                .add(product.productOfQuantity(numberOfFullPriceProducts(product.getQuantity())))
                .add(product.freeProduct(numberOfFreeProducts(product.getQuantity())))
                .asList();
    }

    private int numberOfFullPriceProducts(int quantity) {
        return quantity - numberOfFreeProducts(quantity);
    }

    private int numberOfFreeProducts(int quantity) {
        return quantity / 3;
    }
}