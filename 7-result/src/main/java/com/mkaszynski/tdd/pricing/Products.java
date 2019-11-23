package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Products implements Iterable<Product> {
    private final Map<Product.Key, Product> map = new HashMap<>();

    public void add(Product product) {
        Product.Key key = product.key();
        if (product.quantity() != 0) {
            if (map.containsKey(key)) {
                map.put(key, map.get(key).addQuantity(product));
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
}