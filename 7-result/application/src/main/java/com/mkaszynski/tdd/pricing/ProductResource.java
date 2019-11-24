package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.Product;
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
    Product addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return productRepository.getProduct(product.getName());
    }

    @GetMapping("/product/{name}")
    Product get(@PathVariable String name) {
        return productRepository.getProduct(name);
    }
}
