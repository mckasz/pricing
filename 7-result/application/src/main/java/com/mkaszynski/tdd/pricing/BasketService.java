package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.AddProductCommand;
import com.mkaszynski.tdd.pricing.dto.BasketSummary;
import com.mkaszynski.tdd.pricing.dto.SummaryItem;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
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
        product = product.samePrice(command.getQuantity());

        basket.add(product);

        return basketRepository.save(basket);
    }

    public BasketSummary summary(Long basketId) {
        Basket basket = basketRepository.getBasket(basketId);

        List<Product> products = basket.products();

        List<SummaryItem> items = products.stream()
                                          .map(Product::toSummaryItem)
                                          .collect(toList());
        return new BasketSummary(items);
    }
}
