package com.mkaszynski.tdd.pricing;

import java.time.LocalTime;

public class HappyHour {
    private final LocalTime start;
    private final LocalTime end;

    public HappyHour(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    LocalTime getStart() {
        return start;
    }

    LocalTime getEnd() {
        return end;
    }


    static class HappyHourBuilder {

        private LocalTime start;

        static HappyHourBuilder from(String start) {
            HappyHourBuilder builder = new HappyHourBuilder();
            builder.start = LocalTime.parse(start);
            return builder;
        }

        HappyHour to(String end) {
            return new HappyHour(start, LocalTime.parse(end));
        }
    }

}
