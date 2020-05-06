package com.mkaszynski.tdd.pricing;

import lombok.Value;

import java.util.List;

@Value
class RepricedProductsEvent implements Event {
    private final List<Product> products;
}
