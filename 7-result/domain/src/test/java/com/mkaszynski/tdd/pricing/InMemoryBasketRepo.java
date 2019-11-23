package com.mkaszynski.tdd.pricing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class InMemoryBasketRepo implements BasketRepository {
    private final Map<Long, Basket> map = new HashMap<>();

    @Override
    public Basket getBasket(Long basketId) {
        return map.getOrDefault(basketId, new Basket((long) map.size(), Collections.emptyList(), Campaign.emptyCampaign()));
    }

    @Override
    public Long save(Basket basket) {
        map.put(basket.id(), basket);
        return basket.id();
    }
}
