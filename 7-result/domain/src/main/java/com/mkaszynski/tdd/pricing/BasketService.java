package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.AddProductCommand;
import com.mkaszynski.tdd.pricing.dto.BasketSummary;
import com.mkaszynski.tdd.pricing.dto.Product;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    public BasketService(BasketRepository basketRepository, ProductRepository productRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
    }

    public Long add(AddProductCommand command) {
        Basket basket = basketRepository.getBasket(command.getBasketId());
        Product product = productRepository.getProduct(command.getName());
        SelectedProduct selectedProduct = product.buildSelectedProduct(command.getQuantity());

        basket.add(selectedProduct);

        return basketRepository.save(basket);
    }

    public BasketSummary summary(Long basketId) {
        Basket basket = basketRepository.getBasket(basketId);

        List<SelectedProduct> products = basket.products();

        return new BasketSummary(products.stream()
                                         .map(SelectedProduct::toSummaryItem)
                                         .collect(toList()));
    }
}
