package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ReplaceCamelCaseTest {

    // parametrized tests are nice and concise but we are loosing information
    // about test purpose, we need to either add comment, or put fancy arguments like here
    @ParameterizedTest()
    @CsvSource({
            "oneCapital,one capital",
            "twoCapitalNames,two capital names",
            "FirstCapital,first capital",
            "oneDigit1,one digit 1",
            "threeDigits123,three digits 123",
            "textAfterDigit123test,text after digit 123 test",
            "textAfterDigit1test,text after digit 1 test",
    })
    void replaceNames(String input, String expectedOutput) {
        SeparatedName replacer = new SeparatedName(input);

        String replaced = replacer.value();

        assertThat(replaced).isEqualTo(expectedOutput);
    }
}