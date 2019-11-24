package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SelectedProducts implements Iterable<SelectedProduct> {
    private final Map<SelectedProduct.Key, SelectedProduct> map = new HashMap<>();

    public void add(SelectedProduct product) {
        SelectedProduct.Key key = product.key();
        if (product.quantity() != 0) {
            if (map.containsKey(key)) {
                map.put(key, map.get(key).addQuantity(product));
            } else {
                map.put(key, product);
            }
        }
    }

    public List<SelectedProduct> asList() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Iterator<SelectedProduct> iterator() {
        return map.values().iterator();
    }
}