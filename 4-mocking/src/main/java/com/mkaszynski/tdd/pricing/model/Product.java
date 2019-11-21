package com.mkaszynski.tdd.pricing.model;

import lombok.Value;

@Value
public class Product {
    private final String name;
    private final int price;
    private final ProductType type;
}
