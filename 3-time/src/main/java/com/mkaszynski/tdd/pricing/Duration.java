package com.mkaszynski.tdd.pricing;

import java.time.LocalTime;

public class Duration {
    private final LocalTime start;
    private final LocalTime end;
    private final TimeProvider timeProvider;

    public Duration(LocalTime start, LocalTime end, TimeProvider timeProvider) {
        this.start = start;
        this.end = end;
        this.timeProvider = timeProvider;
    }

    boolean isIn() {
        LocalTime now = timeProvider.now();
        return start.isBefore(now) && end.isAfter(now);
    }

    static class DurationBuilder {
        private final LocalTime start;
        private LocalTime end;

        public DurationBuilder(LocalTime start) {
            this.start = start;
        }

        static DurationBuilder from(String start) {
            return new DurationBuilder(LocalTime.parse(start));
        }

        DurationBuilder to(String end) {
            this.end = LocalTime.parse(end);
            return this;
        }

        Duration nowIs(TimeProvider timeProvider) {
            return new Duration(start, end, timeProvider);
        }
    }
}
