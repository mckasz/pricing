package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class Products {
    private List<Product> list = new ArrayList<>();

    List<Product> asList() {
        return new ArrayList<>(list);
    }

    void add(Product product) {
        if (product.quantity() != 0) {
            list.add(product);
        }
    }
}
