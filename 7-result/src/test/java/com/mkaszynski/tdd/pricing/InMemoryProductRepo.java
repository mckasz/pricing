package com.mkaszynski.tdd.pricing;

import java.util.HashMap;
import java.util.Map;

class InMemoryProductRepo implements ProductRepository {
    private Map<String, Product> map = new HashMap<>();

    @Override
    public Product getProduct(String name) {
        return map.get(name);
    }

    @Override
    public String save(Product product) {
        map.put(product.name(), product);
        return product.name();
    }
}