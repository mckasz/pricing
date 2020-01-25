package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class Promotion3For2 {

    List<Product> apply(Product product) {
        List<Product> products = new ArrayList<>();
        if (product.quantity() == 3) {
            products.add(product.fullPrice(2));
            products.add(product.freeProduct(1));
        } else {
            products.add(product);
        }

        return products;
    }

}
