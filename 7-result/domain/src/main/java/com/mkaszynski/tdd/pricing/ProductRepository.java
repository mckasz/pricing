package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.Product;

interface ProductRepository {
    Product getProduct(String name);
    String save(Product product);
}
