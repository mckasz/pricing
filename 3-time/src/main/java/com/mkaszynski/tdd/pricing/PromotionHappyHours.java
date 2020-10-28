package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class PromotionHappyHours {
    List<Product> apply(Product product,
                        Duration happyHours,
                        Discount discount) {
        List<Product> result = new ArrayList<>();
        if (happyHours.isIn()) {
            result.add(product.discountedProduct(discount));
        } else {
            result.add(product);
        }
        return result;
    }
}
