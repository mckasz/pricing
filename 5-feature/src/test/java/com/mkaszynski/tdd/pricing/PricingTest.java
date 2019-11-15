package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PricingTest {

    @Spy
    private Promotion promotion = new TestPromotion();

    @Test
    void appliesPromotionsToProductsDefinedInCampaign() {
        // given
        Campaign bananasCampaign = new Campaign(newArrayList("bananas"), promotion);
        Pricing pricing = new Pricing(bananasCampaign);
        List<Product> products = newArrayList(bananas());

        // when
        List<Product> repricedProducts = pricing.reprice(products);

        // then
        verify(promotion).apply(bananas());
        assertThat(repricedProducts).containsOnly(bananas());
    }

    @Test
    void doesNotApplyPromotionsToProductsThatAreNotDefinedInCampaign() {
        // given
        Campaign orangesCampaign = new Campaign(newArrayList("oranges"), promotion);
        Pricing pricing = new Pricing(orangesCampaign);
        List<Product> products = newArrayList(bananas());

        // when
        List<Product> repricedProducts = pricing.reprice(products);

        // then
        verify(promotion, never()).apply(bananas());
        assertThat(repricedProducts).containsOnly(bananas());
    }

    @Test
    void returnsProductAfterPromotion() {
        // given
        List<Product> productsAfterPromotion = newArrayList(bananasWithDiscount());
        when(promotion.apply(any())).thenReturn(productsAfterPromotion);
        Campaign bananasCampaign = new Campaign(newArrayList("bananas"), promotion);
        Pricing pricing = new Pricing(bananasCampaign);
        List<Product> products = newArrayList(bananas());

        // when
        List<Product> repricedProducts = pricing.reprice(products);

        // then
        assertThat(repricedProducts).containsOnly(bananasWithDiscount());
    }

    private Product bananas() {
        return bananas(200);
    }
    private Product bananasWithDiscount() {
        return bananas(180);
    }
    private Product bananas(int price) {
        return new Product("bananas", 5, price, Product.Type.FOOD);
    }

    private class TestPromotion implements Promotion {


        @Override
        public List<Product> apply(Product product) {
            return newArrayList(product);
        }
    }
}