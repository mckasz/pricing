package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

class SeparateNameTest {

    @CsvSource({
            "one, one",
            "sampleTest,sample Test",
            "threeWordCamel,three Word Camel",

            "First,First",
    })
    @ParameterizedTest
    void shouldReturnSameName_forSingleWordName(String input, String expectedOutput) {
        SeparateName testName = SeparateName.of(input);

        String transformed = testName.value();

        assertThat(transformed).isEqualTo(expectedOutput);
    }

    @Test
    void shouldReturnNull_forNullInput() {
        SeparateName testName = SeparateName.of(null);

        String transformed = testName.value();

        assertThat(transformed).isEqualTo(null);
    }

}