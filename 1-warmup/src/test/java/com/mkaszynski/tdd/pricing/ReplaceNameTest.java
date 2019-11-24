package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReplaceNameTest {
    @Test
    void shouldReturnNameWhenNameInConstructor() {
        ReplaceName replaceName = new ReplaceName("name");

        String result = replaceName.value();

        assertThat(result).isEqualTo("name");
    }

    @Test
    void shouldReturnSeparatedNameWhenCamelCaseNameInConstructor() {
        ReplaceName replaceName = new ReplaceName("nameOne");

        String result = replaceName.value();

        assertThat(result).isEqualTo("name One");
    }
}