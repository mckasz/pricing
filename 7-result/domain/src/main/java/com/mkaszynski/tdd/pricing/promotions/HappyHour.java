package com.mkaszynski.tdd.pricing.promotions;

import java.time.LocalTime;

import static java.lang.String.format;

class HappyHour {
    private final LocalTime from;
    private final LocalTime to;

    private HappyHour(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
    }

    LocalTime start() {
        return from;
    }

    LocalTime end() {
        return to;
    }

    static final class HappyHourBuilder {
        private LocalTime from;

        private HappyHourBuilder() {
        }

        HappyHour to(String time) {
            LocalTime to = LocalTime.parse(time);
            if (from.isAfter(to)) {
                throw new IllegalArgumentException(format("Happy hour start time %s is after end time %s", from, to));
            }
            return new HappyHour(from, to);
        }

        static HappyHourBuilder from(String time) {
            HappyHourBuilder builder = new HappyHourBuilder();
            builder.from = LocalTime.parse(time);
            return builder;
        }
    }
}
