package com.mkaszynski.tdd.pricing;

import java.util.HashMap;
import java.util.Map;

import static com.mkaszynski.tdd.pricing.Campaign.emptyCampaign;
import static java.util.Collections.emptyList;

class InMemoryBasketRepo implements BasketRepository {
    private Map<Long, Basket> map = new HashMap<>();

    @Override
    public Basket getBasket(Long basketId) {
        return map.getOrDefault(basketId, emptyBasket());
    }

    @Override
    public Long save(Basket basket) {
        long id = map.size();
        map.put(id, basket);
        return id;
    }

    private Basket emptyBasket() {
        return new Basket(1L, emptyList(), emptyCampaign());
    }
}
