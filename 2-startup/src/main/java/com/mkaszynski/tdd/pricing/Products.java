package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class Products {
    List<Product> list = new ArrayList<>();

    Products add(Product product) {
        if (product.getQuantity() > 0) {
            list.add(product);
        }
        return this;
    }

    List<Product> asList() {
        return list;
    }
}
