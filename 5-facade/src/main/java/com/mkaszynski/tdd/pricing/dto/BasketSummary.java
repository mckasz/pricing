package com.mkaszynski.tdd.pricing.dto;

import lombok.Value;

import java.util.List;

@Value
class BasketSummary {
    List<SummaryItem> items;
}
