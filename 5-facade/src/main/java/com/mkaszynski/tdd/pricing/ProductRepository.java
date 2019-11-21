package com.mkaszynski.tdd.pricing;

interface ProductRepository {
    Product getProduct(String name);
    String save(Product product);
}
