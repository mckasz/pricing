package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

class Promotion3for2 {

    List<Product> apply(Product product) {
        int quantity = product.quantity();
        if (quantity == 3) {
            List<Product> result = new ArrayList<>();

            result.add(new Product(product.name(), product.getPrice(), 2));
            result.add(new Product(product.name(), 0, 1));

            return result;
        }

        return singletonList(product);
    }

}
