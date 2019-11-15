package com.mkaszynski.tdd.pricing;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

class HappyHours implements Promotion {

    private final Duration duration;
    private final Discount discount;
    private final TimeProvider timeProvider;

    public HappyHours(Duration duration,
                      Discount discount,
                      TimeProvider timeProvider) {
        this.duration = duration;
        this.discount = discount;
        this.timeProvider = timeProvider;
    }

    @Override
    public List<Product> apply(Product product) {
        List<Product> result = new ArrayList<>();
        if (inHappyHours() && product.isLiquid()) {
            result.add(product.applyDiscount(this.discount));
        } else {
            result.add(product);
        }
        return result;
    }

    private boolean inHappyHours() {
        return duration.start.isBefore(timeProvider.now()) && duration.end.isAfter(timeProvider.now());
    }

    static class Duration {
        private final LocalTime start;
        private final LocalTime end;

        Duration(LocalTime start, LocalTime end) {
            if (start.isAfter(end)) {
                throw new IllegalArgumentException(format("Start [%s] time cannot be after end time [%s]", start, end));
            }
            this.start = start;
            this.end = end;
        }

        static class DurationBuilder {
            private LocalTime start;

            DurationBuilder(LocalTime start) {
                this.start = start;
            }

            static DurationBuilder from(String start) {
                return new DurationBuilder(LocalTime.parse(start));
            }

            Duration to(String end) {
                return new Duration(this.start, LocalTime.parse(end));
            }
        }
    }
}
