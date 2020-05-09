package com.mkaszynski.tdd.pricing;

import java.time.LocalTime;

public interface TimeProvider {
    LocalTime now();
}
