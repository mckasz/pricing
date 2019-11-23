package com.mkaszynski.tdd.pricing;

interface BasketRepository {
    Basket getBasket(Long basketId);
    Long save(Basket basket);
}
