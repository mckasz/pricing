package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.Product;

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
        map.put(product.getName(), product);
        return product.getName();
    }
}
