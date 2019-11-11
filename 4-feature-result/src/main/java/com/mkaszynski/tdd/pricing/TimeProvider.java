package com.mkaszynski.tdd.pricing;

import java.time.LocalTime;

public interface TimeProvider {
    default LocalTime now() {
        return LocalTime.now();
    }
}
