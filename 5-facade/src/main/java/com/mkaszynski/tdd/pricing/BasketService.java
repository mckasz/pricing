package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.AddProductCommand;
import com.mkaszynski.tdd.pricing.dto.BasketSummary;

class BasketService {
    BasketRepository basketRepository;
    ProductRepository productRepository;

    void add(AddProductCommand command) {
        Product product = productRepository.getProduct(command.getName());
        Basket basket = basketRepository.getBasket(command.getBasketId());

        basket.add(product.toSelectedProduct(command.getQuantity()));

        basketRepository.save(basket);
    }

    BasketSummary summary(long l) {
        return null;
    }
}
