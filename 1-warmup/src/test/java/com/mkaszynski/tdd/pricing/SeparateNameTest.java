package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SeparateNameTest {

    @ParameterizedTest
    @CsvSource({"name,name",
                "oneCapital,one Capital",
                "OneCapital,One Capital",
                "oneDigit1,one Digit 1",
                "1oneDigit,1 one Digit",
                "10oneDigit,10 one Digit",
    })
    void shouldSplitStringByCapitalLettersAndDigits(String input, String expectedResult) {
        SeparateName separateName = new SeparateName(input);

        String result = separateName.value();

        assertThat(result).describedAs("").isEqualTo(expectedResult);
    }
}