package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(SeparateNameGenerator.class)
class Promotion3for2Test {
    @Test
    void shouldReturn1FreeProduct_and2FullPriceProducts_for3SameProducts() {
        Promotion3for2 promotion = new Promotion3for2();

        List<Product> result = promotion.apply(new Product("ProductName", 220, 1));

        assertThat(result).containsOnly(new Product("ProductName", 220, 1));
    }
}
