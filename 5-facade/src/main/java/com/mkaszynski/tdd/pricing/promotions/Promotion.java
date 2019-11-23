package com.mkaszynski.tdd.pricing.promotions;

import com.mkaszynski.tdd.pricing.SelectedProduct;

import java.util.List;

public interface Promotion {
    List<SelectedProduct> apply(SelectedProduct product);
}
