package com.mkaszynski.tdd.pricing;

public interface ProductRepository {
    Product getProduct(String name);
    String save(Product product);
}
