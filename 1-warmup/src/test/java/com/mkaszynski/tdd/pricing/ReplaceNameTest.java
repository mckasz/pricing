package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ReplaceNameTest {

    @ParameterizedTest
    @CsvSource({
            "name,name",
            "nameOne,name One",
            "digit1,digit 1",
            "nameOneTwo,name One Two",
            "Capital,Capital",
            "digit123,digit 123",
            "digit123text,digit 123 text",
    })
    void shouldReplaceCapitalLettersAndNumbersWithSpace(String input, String expectedResult) {
        ReplaceName replaceName = new ReplaceName(input);

        String result = replaceName.value();

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldReturnNullWhenNullInInput() {
        ReplaceName replaceName = new ReplaceName(null);

        String result = replaceName.value();

        assertThat(result).isEqualTo(null);
    }
}