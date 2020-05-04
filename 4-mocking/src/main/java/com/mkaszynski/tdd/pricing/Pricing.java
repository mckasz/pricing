package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class Pricing {

    private final Promotion promotion;

    Pricing(Promotion promotion) {
        this.promotion = promotion;
    }

    List<Product> reprice(List<Product> input) {
        List<Product> result = new ArrayList<>();
        for (Product product : input) {
            result.addAll(promotion.apply(product));
        }
        return result;
    }

    String prepareReceipt(List<Product> input) {
        StringBuilder result = new StringBuilder();
        for (Product product : input) {
            double price = (double) (product.getPrice()) / 100;
            result.append(" - ").append(product.getName()).append(" ").append(price).append("zł/n");
        }
        return result.toString();
    }

}
