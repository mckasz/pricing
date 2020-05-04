package com.mkaszynski.tdd.pricing;

import lombok.Value;

@Value
public class Product {
    private final String name;
    private final int price;
}
