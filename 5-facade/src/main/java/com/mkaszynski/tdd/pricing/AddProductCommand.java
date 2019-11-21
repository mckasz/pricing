package com.mkaszynski.tdd.pricing;

import lombok.Value;

@Value
class AddProductCommand {
    private final Long basketId;
    private final String name;
    private final int quantity;
}
