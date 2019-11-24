package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.AddProductCommand;
import com.mkaszynski.tdd.pricing.dto.BasketSummary;
import com.mkaszynski.tdd.pricing.dto.SummaryItem;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BasketServiceScenarioTest {

    @Test
    void shouldStoreOneProduct_whenAddedToEmptyBasket() {
        BasketService basketService = new BasketService();

        basketService.add(new AddProductCommand(1L, "Butter", 1));
        BasketSummary summary = basketService.summary(1L);

        assertThat(summary.getItems()).containsOnly(new SummaryItem("Butter", 220, 1));
    }
}