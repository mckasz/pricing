package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class Promotion3For2 {

    List<Product> apply(Product product) {
        List<Product> products = new ArrayList<>();
        products.add(product.freeProduct(freeQuantity(product.quantity())));
        products.add(product.fullPrice(fullPriceQuantity(product.quantity())));
        return products;
    }

    private int fullPriceQuantity(int quantity) {
        return quantity - freeQuantity(quantity);
    }

    private int freeQuantity(int quantity) {
        return quantity / 3;
    }

}
