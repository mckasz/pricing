package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mkaszynski.tdd.pricing.Product.Type.DRINK;
import static com.mkaszynski.tdd.pricing.Product.Type.FOOD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

class PricingTest {

    @Test
    void testReceiptForOneProduct() {
        Pricing pricing = new Pricing(null, null);

        String receipt = pricing.getReceipt(newArrayList(foodProduct()));

        assertThat(receipt).isEqualTo("ProductName 20 zł");
    }

    @Disabled
    @Test
    void testRepriceForSimplePromotion() {
        Pricing pricing = new Pricing(null, null);

        List<Product> result = pricing.reprice(newArrayList(foodProduct()));

        assertThat(result).containsOnly(foodProduct());
    }

    @Test
    void testPromotionIsNotCalledForDrinkProducts() {
//        PromotionSpy promotion = new PromotionSpy();
//        Pricing pricing = new Pricing(promotion, new DummyEventSender());
//
//        pricing.reprice(newArrayList(drinkProduct()));
//
//        assertThat(promotion.applyCount()).isEqualTo(0);
    }

    @Test
    void testPromotionCalledForDrinkProductsWithGivenProduct() {
//        PromotionMock promotion = new PromotionMock();
//        Pricing pricing = new Pricing(promotion, new DummyEventSender());
//
//        pricing.reprice(newArrayList(foodProduct()));
//
//        assertThat(promotion.applyCount()).isEqualTo(1);
//        assertThat(promotion.applyArguments()).containsOnly(foodProduct());
    }

    private Product foodProduct() {
        return new Product("ProductName", 20, FOOD);
    }

    private Product drinkProduct() {
        return new Product("ProductName", 10, DRINK);
    }
}
