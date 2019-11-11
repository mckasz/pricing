package com.mkaszynski.tdd.pricing;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.mkaszynski.tdd.pricing.Product.Type.LIQUID;
import static java.lang.String.format;

class HappyHours implements Promotion {

    private final Duration duration;
    private final Discount priceReduction;
    private final TimeProvider timeProvider;

    public HappyHours(Duration duration,
                      Discount priceReduction,
                      TimeProvider timeProvider) {
        this.duration = duration;
        this.priceReduction = priceReduction;
        this.timeProvider = timeProvider;
    }

    @Override
    public List<Product> apply(Product product) {
        List<Product> result = new ArrayList<>();
        if (product.type() == LIQUID && duration.start.isBefore(timeProvider.now()) && duration.end.isAfter(timeProvider.now())) {
            int discountedPrice = (int) (product.price() * priceReduction.multiplier());
            result.add(product.discountedProduct(discountedPrice));
        } else {
            result.add(product);
        }
        return result;
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
