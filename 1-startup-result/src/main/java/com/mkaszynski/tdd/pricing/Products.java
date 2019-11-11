package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class Products {
    private List<Product> list = new ArrayList<>();

    void add(Product product) {
        if (product.quantity() != 0) {
            list.add(product);
        }
    }

    List<Product> asList() {
        return new ArrayList<>(list);
    }
}
