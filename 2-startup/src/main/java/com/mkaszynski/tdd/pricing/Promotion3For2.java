package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class Promotion3For2 {

    List<Product> apply(Product product) {
        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }
}
