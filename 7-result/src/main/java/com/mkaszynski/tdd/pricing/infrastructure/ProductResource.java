package com.mkaszynski.tdd.pricing.infrastructure;

import com.mkaszynski.tdd.pricing.Product;
import com.mkaszynski.tdd.pricing.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductResource {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/product/")
    void addProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    @GetMapping("/product/{name}")
    Product get(@PathVariable String name) {
        return productRepository.getProduct(name);
    }
}
