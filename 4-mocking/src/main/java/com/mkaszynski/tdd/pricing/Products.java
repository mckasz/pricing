package com.mkaszynski.tdd.pricing;

import lombok.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Products implements Iterable<Product> {
    private final Map<ProductKey, Product> map = new HashMap<>();

    public void add(Product product) {
        ProductKey key = key(product);
        if (product.quantity() != 0) {
            if (map.containsKey(key)) {
                map.put(key, map.get(key).addQuantity(product.getQuantity()));
            } else {
                map.put(key, product);
            }
        }
    }

    public List<Product> asList() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Iterator<Product> iterator() {
        return map.values().iterator();
    }

    private static ProductKey key(Product product) {
        return new ProductKey(product.getName(), product.getPrice());
    }

    @Value
    private static class ProductKey {

        private final String name;
        private final int price;

        ProductKey(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }
}