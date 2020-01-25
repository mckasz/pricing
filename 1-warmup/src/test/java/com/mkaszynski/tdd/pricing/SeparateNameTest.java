package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SeparateNameTest {

    @Test
    void shouldReturnValueFromConstructor() {
        SeparateName separateName = new SeparateName("name");

        String result = separateName.value();

        assertThat(result).isEqualTo("name");
    }

    @Test
    void shouldSplitTwoWords() {
        SeparateName separateName = new SeparateName("oneCapital");

        String result = separateName.value();

        assertThat(result).isEqualTo("one Capital");
    }
}