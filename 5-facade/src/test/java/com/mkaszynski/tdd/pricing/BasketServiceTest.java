package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

class BasketServiceTest {

    @DisplayName("empty basket, when product is added, there is 1 product in basket")
    @Test
    void addProduct() {
        BasketRepository basketRepository = new InMemoryBasketRepo();
        ProductRepository productRepository = new InMemoryProductRepo();
        productRepository.save(new Product("Butter", 220, 1, Product.Type.FOOD));
        BasketService basketService = new BasketService(basketRepository, productRepository);

        Long basketId = basketService.add(new AddProductCommand(null, "Butter", 1));
        BasketSummary products = basketService.summary(basketId);

        assertThat(products.getItems()).containsOnly(new SummaryItem("Butter", 220, 1));
    }

    @DisplayName("1 butter in basket, when 2 butters are added, there are 3 butters in basket")
    @Test
    void addProduct_basketNotEmpty() {
        BasketRepository basketRepository = new InMemoryBasketRepo();
        basketRepository.save(new Basket(1L, newArrayList(butter()), Campaign.emptyCampaign()));
        ProductRepository productRepository = new InMemoryProductRepo();
        productRepository.save(butter(1));
        BasketService basketService = new BasketService(basketRepository, productRepository);

        Long basketId = basketService.add(new AddProductCommand(1L, "Butter", 2));
        BasketSummary products = basketService.summary(basketId);

        assertThat(products.getItems()).containsOnly(new SummaryItem("Butter", 220, 3));
    }

    private Product butter() {
        return butter(1);
    }

    private Product butter(int quantity) {
        return new Product("Butter", 220, quantity, Product.Type.FOOD);
    }
}