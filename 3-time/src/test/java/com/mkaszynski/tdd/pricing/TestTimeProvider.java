package com.mkaszynski.tdd.pricing;

import java.time.LocalTime;

class TestTimeProvider implements TimeProvider {

    private final String time;

    private TestTimeProvider(String time) {
        this.time = time;
    }

    static TestTimeProvider nowIs(String time) {
        return new TestTimeProvider(time);
    }

    @Override
    public LocalTime now() {
        return LocalTime.parse(time);
    }
}
