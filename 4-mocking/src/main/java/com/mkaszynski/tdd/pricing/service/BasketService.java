package com.mkaszynski.tdd.pricing.service;

import com.mkaszynski.tdd.pricing.model.Product;
import com.mkaszynski.tdd.pricing.model.ProductKey;
import com.mkaszynski.tdd.pricing.model.SelectedProduct;
import com.mkaszynski.tdd.pricing.repository.ProductCatalog;
import com.mkaszynski.tdd.pricing.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketService {
    private final ProductRepository productRepository;
    private final ProductCatalog productCatalog;

    BasketService(ProductRepository productRepository, ProductCatalog productCatalog) {
        this.productRepository = productRepository;
        this.productCatalog = productCatalog;
    }

    public void add(Long basketId, String name, int quantity) {
        List<SelectedProduct> products = productRepository.getProducts(basketId);
        Product product = productCatalog.getProduct(name);
        SelectedProduct selectedProduct = new SelectedProduct(product.getName(), product.getPrice(), quantity, product.getType());
        Map<ProductKey, SelectedProduct> map = buildProductMap(products);
        mergeProducts(selectedProduct, map);
        productRepository.save(map.values());
        productCatalog.save(product);
    }

    private Map<ProductKey, SelectedProduct> buildProductMap(List<SelectedProduct> products) {
        Map<ProductKey, SelectedProduct> map = new HashMap<>();
        for (SelectedProduct product : products) {
            map.put(key(product), product);
        }
        return map;
    }

    private void mergeProducts(SelectedProduct product, Map<ProductKey, SelectedProduct> map) {
        if (product.quantity() != 0) {
            ProductKey key = key(product);
            if (map.containsKey(key)) {
                SelectedProduct selectedProduct = map.get(key);
                selectedProduct.setQuantity(selectedProduct.getQuantity() + product.getQuantity());
                map.put(key, selectedProduct);
            } else {
                map.put(key, product);
            }
        }
    }

    private static ProductKey key(SelectedProduct product) {
        return new ProductKey(product.getName(), product.getPrice());
    }
}
