package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class PromotionSpy implements Promotion {

    private int applyCnt = 0;

    @Override
    public List<Product> apply(Product product) {
        List<Product> result = new ArrayList<>();
        result.add(product);
        applyCnt++;
        return result;
    }

    int getApplyCnt() {
        return applyCnt;
    }
}
