package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class StubPromotion implements Promotion {
    @Override
    public List<Product> apply(Product product) {
        List<Product> result = new ArrayList<>();
        result.add(product);
        return result;
    }
}
