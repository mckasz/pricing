package com.mkaszynski.tdd.pricing.service;

import com.mkaszynski.tdd.pricing.model.Product;
import com.mkaszynski.tdd.pricing.model.ProductType;
import com.mkaszynski.tdd.pricing.repository.ProductCatalog;
import com.mkaszynski.tdd.pricing.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

class BasketServiceTest {

    @Test
    void shouldStoreOneProductInRepository_whenAddedProductToEmptyBasket() {
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        ProductCatalog productCatalog = Mockito.mock(ProductCatalog.class);
        Mockito.when(productCatalog.getProduct("Butter")).thenReturn(new Product("Butter", 220, ProductType.FOOD));
        BasketService basketService = new BasketService(productRepository, productCatalog);

        basketService.add(1L, "Butter", 1);

        Mockito.verify(productRepository).save(any());
    }
}