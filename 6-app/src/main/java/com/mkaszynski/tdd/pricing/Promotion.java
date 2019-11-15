package com.mkaszynski.tdd.pricing;

import java.util.List;

public interface Promotion {
    List<Product> apply(Product product);
}
