package com.mkaszynski.tdd.pricing;

public interface BasketRepository {
    Basket getBasket(Long basketId);
    Long save(Basket basket);
}
