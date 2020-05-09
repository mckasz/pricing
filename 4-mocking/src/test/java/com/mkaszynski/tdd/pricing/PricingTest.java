package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.mkaszynski.tdd.pricing.Product.Type.DRINK;
import static com.mkaszynski.tdd.pricing.Product.Type.FOOD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PricingTest {

    @SuppressWarnings("unused") // used in ...
    @Mock
    private EventSender eventSenderDummy;

    @Mock
    private Promotion promotion;

    @InjectMocks
    Pricing pricing;

    @Test
    void testReceiptForOneProduct() {
        String receipt = pricing.getReceipt(newArrayList(foodProduct()));

        assertThat(receipt).isEqualTo("ProductName 20 zł");
    }

    @Test
    void testRepriceForSimplePromotion() {
        when(promotion.apply(any())).thenReturn(newArrayList(foodProduct()));

        List<Product> result = pricing.reprice(newArrayList(foodProduct()));

        assertThat(result).containsOnly(foodProduct());
    }

    @Test
    void testPromotionIsNotCalledForDrinkProducts() {
        PromotionSpy promotion = new PromotionSpy();
        Pricing pricing = new Pricing(promotion, new DummyEventSender());

        pricing.reprice(newArrayList(drinkProduct()));

        assertThat(promotion.getApplyCnt()).isEqualTo(0);
    }

    @Test
    void testPromotionIsCalledForFoodProducts() {
        PromotionSpy promotion = new PromotionSpy();
        Pricing pricing = new Pricing(promotion, new DummyEventSender());

        pricing.reprice(newArrayList(foodProduct()));

        assertThat(promotion.getApplyCnt()).isEqualTo(1);
    }

    @Test
    void testPromotionCalledForDrinkProductsWithGivenProduct() {
        PromotionMock promotion = new PromotionMock();
        Pricing pricing = new Pricing(promotion, new DummyEventSender());

        pricing.reprice(newArrayList(foodProduct()));

        assertThat(promotion.getApplyCnt()).isEqualTo(1);
        assertThat(promotion.getApplyArguments()).containsOnly(foodProduct());
    }

    private Product foodProduct() {
        return new Product("ProductName", 20, FOOD);
    }

    private Product drinkProduct() {
        return new Product("ProductName", 10, DRINK);
    }
}
