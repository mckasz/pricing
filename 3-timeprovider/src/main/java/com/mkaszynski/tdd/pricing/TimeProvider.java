package com.mkaszynski.tdd.pricing;

import java.time.LocalTime;

interface TimeProvider {
    LocalTime now();
}
