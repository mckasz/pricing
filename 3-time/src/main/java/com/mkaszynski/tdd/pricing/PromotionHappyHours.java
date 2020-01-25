package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class PromotionHappyHours implements Promotion {

    private Discount discount;

    public PromotionHappyHours(Discount discount) {
        this.discount = discount;
    }

    // Liquid products should be discounted when it is between 12 and 14
    @Override
    public List<Product> apply(Product product) {
        List<Product> result = new ArrayList<>();

        if (product.type() == Product.Type.LIQUID) {
            result.add(product.discountedProduct(discount));
        } else {
            result.add(product);
        }

        return result;
    }

}
