package com.mkaszynski.tdd.pricing.service;

import com.mkaszynski.tdd.pricing.model.Product;
import com.mkaszynski.tdd.pricing.model.ProductType;
import com.mkaszynski.tdd.pricing.model.SelectedProduct;
import com.mkaszynski.tdd.pricing.repository.ProductCatalog;
import com.mkaszynski.tdd.pricing.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductCatalog productCatalog;
    @InjectMocks
    private BasketService basketService;
    @Captor
    private ArgumentCaptor<List<SelectedProduct>> argumentCaptor;

    @Test
    void shouldStoreOneProductInRepository_whenAddedProductToEmptyBasket() {
        when(productCatalog.getProduct("Butter")).thenReturn(butter());

        basketService.add(1L, "Butter", 1);

        verify(productRepository).save(argumentCaptor.capture());
        List<SelectedProduct> savedList = argumentCaptor.getValue();
        assertThat(savedList).containsOnly(selectedButter(1));
    }

    @Test
    void shouldStoreTwoProductsInRepository_whenAddedProductToEmptyBasketWithOneProduct() {
        when(productCatalog.getProduct("Butter")).thenReturn(butter());
        when(productRepository.getProducts(ArgumentMatchers.any())).thenReturn(newArrayList(selectedButter(1)));

        basketService.add(1L, "Butter", 1);

        verify(productRepository).save(argumentCaptor.capture());
        List<SelectedProduct> savedList = argumentCaptor.getValue();
        assertThat(savedList).containsOnly(selectedButter(2));
    }


    private Product butter() {
        return new Product("Butter", 220, ProductType.FOOD);
    }

    private SelectedProduct selectedButter(int quantity) {
        return new SelectedProduct("Butter", 220, quantity, ProductType.FOOD);
    }
}