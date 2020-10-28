package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class PromotionMock implements Promotion {
    private int applyCnt = 0;
    private List<Product> applyArguments = new ArrayList<>();

    @Override
    public List<Product> apply(Product product) {
        List<Product> result = new ArrayList<>();
        result.add(product);
        applyCnt++;
        applyArguments.add(product);
        return result;
    }

    int getApplyCnt() {
        return applyCnt;
    }

    List<Product> getApplyArguments() {
        return applyArguments;
    }
}
