package com.mkaszynski.tdd.pricing.dto;

import lombok.Value;

@Value
public
class AddProductCommand {
    private final Long basketId;
    private final String name;
    private final int quantity;
}
