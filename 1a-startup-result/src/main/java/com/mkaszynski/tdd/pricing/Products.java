package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class Products {
    private final List<Product> list = new ArrayList<>();

    void add(Product e) {
        if (e.quantity() != 0) {
            list.add(e);
        }
    }

    List<Product> asList() {
        return new ArrayList<>(list);
    }
}