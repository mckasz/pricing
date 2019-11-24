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

        List<Product> result = promotion.apply(butter());

        assertThat(result).containsOnly(butter());
    }

    @Test
    void shouldReturnFullPriceProductAndFreeProduct_whenQuantityEqualsThree() {
        Promotion3for2 promotion = new Promotion3for2();

        List<Product> result = promotion.apply(new Product("Butter", 220, 3));

        assertThat(result).containsOnly(new Product("Butter", 220, 2),
                                        new Product("Butter", 0, 1));
    }

    private Product butter() {
        return new Product("Butter", 220, 1);
    }
}