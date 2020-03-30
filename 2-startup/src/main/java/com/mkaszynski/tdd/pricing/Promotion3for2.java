package com.mkaszynski.tdd.pricing;

import java.util.List;

class Promotion3for2 {
    List<Product> apply(Product product) {
        Products products = new Products();
        products.add(product.fullPrice(numberOfFullPriceProducts(product.quantity())));
        products.add(product.free(numberOfFreeProducts(product.quantity())));
        return products.asList();
    }

    private int numberOfFullPriceProducts(int quantity) {
        return quantity - numberOfFreeProducts(quantity);
    }

    private int numberOfFreeProducts(int quantity) {
        return quantity / 3;
    }
}
