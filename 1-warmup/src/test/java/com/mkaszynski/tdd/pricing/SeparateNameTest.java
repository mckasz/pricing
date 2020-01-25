package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SeparateNameTest {

    @ParameterizedTest
    @CsvSource({"name,name",
                "oneCapital,one Capital",
                "OneCapital,One Capital"
    })
    void shouldSplitStringByCapitalLettersAndDigits(String input, String expectedResult) {
        SeparateName separateName = new SeparateName(input);

        String result = separateName.value();

        assertThat(result).isEqualTo(expectedResult);
    }
}