package com.mkaszynski.tdd.pricing.promotions;

import com.mkaszynski.tdd.pricing.Product;

import java.util.List;

public interface Promotion {
    List<Product> apply(Product product);
}
