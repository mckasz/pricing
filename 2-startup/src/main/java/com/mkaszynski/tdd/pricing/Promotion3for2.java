package com.mkaszynski.tdd.pricing;

import java.util.List;

class Promotion3for2 {

    List<Product> apply(Product product) {
        Products result = new Products();
        result.add(product.freeProduct(numberOfFreeProducts(product.quantity())));
        result.add(product.fullPriceProduct(numberOfFullPriceProducts(product.quantity())));
        return result.asList();
    }

    private int numberOfFullPriceProducts(int quantity) {
        return quantity - numberOfFreeProducts(quantity);
    }

    private int numberOfFreeProducts(int quantity) {
        return quantity / 3;
    }

}
