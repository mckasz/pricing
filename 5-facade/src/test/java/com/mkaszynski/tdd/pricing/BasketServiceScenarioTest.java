package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.AddProductCommand;
import com.mkaszynski.tdd.pricing.dto.BasketSummary;
import com.mkaszynski.tdd.pricing.dto.SummaryItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mkaszynski.tdd.pricing.Campaign.emptyCampaign;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BasketServiceScenarioTest {

    private BasketRepository basketRepository = new InMemoryBasketRepo();
    private ProductRepository productRepository = new InMemoryProductRepository();

    private BasketService basketService = new BasketService(basketRepository, productRepository);


    @Test
    void shouldStoreOneProduct_whenAddedToEmptyBasket() {
        productRepository.save(new Product("Butter", 220, SelectedProduct.Type.FOOD));

        basketService.add(new AddProductCommand(0L, "Butter", 1));
        BasketSummary summary = basketService.summary(0L);

        assertThat(summary.getItems()).containsOnly(new SummaryItem("Butter", 220, 1));
    }

    @Test
    void shouldStoreOneProduct_whenAddedToEmptyBasket1() {
        productRepository.save(new Product("Butter", 220, SelectedProduct.Type.FOOD));

        basketService.add(new AddProductCommand(0L, "Butter", 1));
        BasketSummary summary = basketService.summary(0L);

        assertThat(summary.getItems()).containsOnly(new SummaryItem("Butter", 220, 1));
    }

    private Basket emptyBasket() {
        return new Basket(1L, emptyList(), emptyCampaign());
    }

    private Product butter() {
        return new Product("Butter", 220, SelectedProduct.Type.FOOD);
    }

}