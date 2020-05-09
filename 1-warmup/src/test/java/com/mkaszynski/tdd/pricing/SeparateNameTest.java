package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;


class SeparateNameTest {

    @CsvSource({
            "one,one",
            "oneTwo,one Two",
            "oneTwo,one TWO",
            "oneTwoThree,one Two Three",

            // handles numbers
            "number1,number 1",
            "number1abc,number 1 abc",
            "number12abc,number 12 abc",
            "number12,number 12",
            "12,12",
    })
    @ParameterizedTest
    void shouldReturnAppropriateNameForProvidedInput(String input, String expectedResult) {
        SeparateName separateName = new SeparateName(input);

        String result = separateName.value();

        assertThat(result).isEqualTo(expectedResult);
    }
}
