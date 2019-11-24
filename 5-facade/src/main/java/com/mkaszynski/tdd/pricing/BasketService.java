package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.AddProductCommand;
import com.mkaszynski.tdd.pricing.dto.BasketSummary;
import lombok.RequiredArgsConstructor;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
class BasketService {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    void add(AddProductCommand command) {
        Product product = productRepository.getProduct(command.getName());
        Basket basket = basketRepository.getBasket(command.getBasketId());

        basket.add(product.toSelectedProduct(command.getQuantity()));

        basketRepository.save(basket);
    }

    BasketSummary summary(long basketId) {
        return new BasketSummary(basketRepository.getBasket(basketId)
                                                 .products()
                                                 .stream()
                                                 .map(SelectedProduct::toSummaryItem)
                                                 .collect(toList()));
    }

}
