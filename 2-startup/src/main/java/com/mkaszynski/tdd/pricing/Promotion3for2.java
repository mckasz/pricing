package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class Promotion3for2 {

    List<Product> apply(Product product) {
        int quantity = product.quantity();
        List<Product> result = new ArrayList<>();
        if (quantity == 3) {
            result.add(product.fullPriceProduct(2));
            result.add(product.freeProduct(1));
        } else if (quantity == 1) {
            result.add(product.fullPriceProduct(1));
        }
        return result;
    }

}
