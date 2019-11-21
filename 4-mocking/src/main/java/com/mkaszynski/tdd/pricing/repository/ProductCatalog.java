package com.mkaszynski.tdd.pricing.repository;

import com.mkaszynski.tdd.pricing.model.Product;

public interface ProductCatalog {
    Product getProduct(String name);
}
