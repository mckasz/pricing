package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(CamelCaseAndUnderscoresGenerator.class)
class Promotion3for2Test {

    @Test
    void shouldReturnFullPriceProduct_whenQuantityEqualsOne() {
        Promotion3for2 promotion = new Promotion3for2();

        List<Product> result = promotion.apply(butter(1));

        assertThat(result).containsOnly(butter(1));
    }

    @Test
    void shouldReturnFullPriceProductAndFreeProduct_whenQuantityEqualsThree() {
        Promotion3for2 promotion = new Promotion3for2();

        List<Product> result = promotion.apply(butter(3));

        assertThat(result).containsOnly(butter(2), freeButter());
    }

    private Product butter(int quantity) {
        return new Product("Butter", 220, quantity);
    }

    private Product freeButter() {
        return new Product("Butter", 0, 1);
    }
}