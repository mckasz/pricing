package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class Promotion3for2 {
    List<Product> apply(Product product) {
        List<Product> result = new ArrayList<>();
        result.add(product);
        return result;
    }
}
