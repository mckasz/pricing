package com.mkaszynski.tdd.pricing;

import java.util.HashMap;
import java.util.Map;

class InMemoryProductRepo implements ProductRepository {
    private Map<String, SelectedProduct> map = new HashMap<>();



    @Override
    public SelectedProduct getProduct(String name) {
        return map.get(name);
    }

    @Override
    public String save(SelectedProduct product) {
        map.put(product.getName(), product);
        return product.getName();
    }
}
