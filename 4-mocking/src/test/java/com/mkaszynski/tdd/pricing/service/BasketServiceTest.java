package com.mkaszynski.tdd.pricing.service;

import com.mkaszynski.tdd.pricing.model.Product;
import com.mkaszynski.tdd.pricing.model.ProductType;
import com.mkaszynski.tdd.pricing.model.SelectedProduct;
import com.mkaszynski.tdd.pricing.repository.ProductCatalog;
import com.mkaszynski.tdd.pricing.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {
    @Mock
    ProductRepository productRepository;
    @Mock
    ProductCatalog productCatalog;
    @InjectMocks
    BasketService basketService;

    @Captor
    ArgumentCaptor<Collection<SelectedProduct>> collectionCaptor;

    @Test
    void shouldAddProductToRepository() {
        ArrayList<SelectedProduct> t = new ArrayList<>();
        when(productRepository.getProducts(anyLong())).thenReturn(t);
        when(productCatalog.getProduct(anyString())).thenReturn(new Product("butter", 220, ProductType.FOOD));

        basketService.add(1L, "butter", 1);

        verify(productRepository).save(collectionCaptor.capture());
        Collection<SelectedProduct> savedProducts = collectionCaptor.getValue();
        assertThat(savedProducts).containsOnly(new SelectedProduct("butter", 220, 1, ProductType.FOOD));

        verify(productCatalog).save(new Product("butter", 220, ProductType.FOOD));
    }
}