package com.mkaszynski.tdd.pricing;

import java.time.LocalTime;

class LocalTimeProvider implements TimeProvider {
    @Override
    public LocalTime now() {
        return LocalTime.now();
    }
}
