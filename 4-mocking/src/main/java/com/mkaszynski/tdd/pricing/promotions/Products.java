package com.mkaszynski.tdd.pricing.promotions;

import com.mkaszynski.tdd.pricing.Product;

import java.util.ArrayList;
import java.util.List;

public class Products {
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