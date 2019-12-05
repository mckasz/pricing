package com.mkaszynski.tdd.pricing.repository;

import com.mkaszynski.tdd.pricing.model.SelectedProduct;

import java.util.Collection;
import java.util.List;

public interface ProductRepository {
    List<SelectedProduct> getProducts(Long basketId);
    void save(Collection<SelectedProduct> values);
}
