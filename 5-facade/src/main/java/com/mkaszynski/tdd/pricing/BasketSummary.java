package com.mkaszynski.tdd.pricing;

import lombok.Value;

import java.util.List;

@Value
class BasketSummary {
    List<SummaryItem> items;
}
