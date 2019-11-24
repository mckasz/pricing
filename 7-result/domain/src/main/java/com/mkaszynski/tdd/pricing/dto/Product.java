package com.mkaszynski.tdd.pricing.dto;

import com.mkaszynski.tdd.pricing.SelectedProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private int price;
    private SelectedProduct.Type type;

    public SelectedProduct buildSelectedProduct(int quantity) {
        return new SelectedProduct(name, price, quantity, type);
    }
}
