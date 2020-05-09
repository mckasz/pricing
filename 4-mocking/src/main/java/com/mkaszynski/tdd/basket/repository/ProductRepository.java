package com.mkaszynski.tdd.basket.repository;

import com.mkaszynski.tdd.basket.model.SelectedProduct;

import java.util.Collection;
import java.util.List;

public interface ProductRepository {
    List<SelectedProduct> getProducts(Long basketId);
    void save(Collection<SelectedProduct> values);
}
