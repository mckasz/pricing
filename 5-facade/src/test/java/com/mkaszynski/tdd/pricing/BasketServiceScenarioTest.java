package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.AddProductCommand;
import com.mkaszynski.tdd.pricing.dto.BasketSummary;
import com.mkaszynski.tdd.pricing.dto.SummaryItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mkaszynski.tdd.pricing.Campaign.emptyCampaign;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasketServiceScenarioTest {

    @Mock
    private BasketRepository basketRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private BasketService basketService;

    @Test
    void shouldStoreOneProduct_whenAddedToEmptyBasket() {
        when(productRepository.getProduct("Butter")).thenReturn(butter());
        when(basketRepository.getBasket(1L)).thenReturn(emptyBasket());

        basketService.add(new AddProductCommand(1L, "Butter", 1));
        BasketSummary summary = basketService.summary(1L);

        assertThat(summary.getItems()).containsOnly(new SummaryItem("Butter", 220, 1));
    }

    private Basket emptyBasket() {
        return new Basket(1L, emptyList(), emptyCampaign());
    }

    private Product butter() {
        return new Product("Butter", 220, SelectedProduct.Type.FOOD);
    }
}