package com.mkaszynski.tdd.basket.repository;

import com.mkaszynski.tdd.basket.model.Product;

public interface ProductCatalog {
    Product getProduct(String name);
}
