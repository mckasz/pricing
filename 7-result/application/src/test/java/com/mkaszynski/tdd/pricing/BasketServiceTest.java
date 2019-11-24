package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.AddProductCommand;
import com.mkaszynski.tdd.pricing.dto.BasketSummary;
import com.mkaszynski.tdd.pricing.dto.Product;
import com.mkaszynski.tdd.pricing.dto.SummaryItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

class BasketServiceTest {
    private BasketRepository basketRepository = new BasketInMemoryRepository();
    private ProductRepository productRepository = new ProductInMemoryRepository();

    @DisplayName("empty basket, when product is added, there is 1 product in basket")
    @Test
    void addProduct() {
        productRepository.save(new Product("Butter", 220, SelectedProduct.Type.FOOD));
        BasketService basketService = new BasketService(basketRepository, productRepository);

        Long basketId = basketService.add(new AddProductCommand(null, "Butter", 1));
        BasketSummary products = basketService.summary(basketId);

        assertThat(products.getItems()).containsOnly(new SummaryItem("Butter", 220, 1));
    }

    @DisplayName("1 butter in basket, when 2 butters are added, there are 3 butters in basket")
    @Test
    void addProduct_basketNotEmpty() {
        basketRepository.save(new Basket(1L, newArrayList(butter().buildSelectedProduct(1)), Campaign.emptyCampaign()));
        productRepository.save(butter());
        BasketService basketService = new BasketService(basketRepository, productRepository);

        Long basketId = basketService.add(new AddProductCommand(1L, "Butter", 2));
        BasketSummary products = basketService.summary(basketId);

        assertThat(products.getItems()).containsOnly(new SummaryItem("Butter", 220, 3));
    }

    private Product butter() {
        return new Product("Butter", 220, SelectedProduct.Type.FOOD);
    }
}