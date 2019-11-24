package com.mkaszynski.tdd.pricing;

import java.time.LocalTime;

class ProductionTimeProvider implements TimeProvider {

    @Override
    public LocalTime now() {
        return LocalTime.now();
    }
}
