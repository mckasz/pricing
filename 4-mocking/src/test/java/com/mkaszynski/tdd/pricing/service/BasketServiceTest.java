package com.mkaszynski.tdd.pricing.service;

import com.mkaszynski.tdd.pricing.model.Product;
import com.mkaszynski.tdd.pricing.model.ProductType;
import com.mkaszynski.tdd.pricing.model.SelectedProduct;
import com.mkaszynski.tdd.pricing.repository.ProductCatalog;
import com.mkaszynski.tdd.pricing.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


class BasketServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductCatalog productCatalog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldStoreOneProductInRepository_whenAddedProductToEmptyBasket() {
        Mockito.when(productCatalog.getProduct("Butter")).thenReturn(new Product("Butter", 220, ProductType.FOOD));
        BasketService basketService = new BasketService(productRepository, productCatalog);

        basketService.add(1L, "Butter", 1);

        ArgumentCaptor<List<SelectedProduct>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(productRepository).save(argumentCaptor.capture());
        List<SelectedProduct> savedList = argumentCaptor.getValue();
        assertThat(savedList).containsOnly(butter());
    }

    private SelectedProduct butter() {
        return new SelectedProduct("Butter", 220, 1, ProductType.FOOD);
    }
}