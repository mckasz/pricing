package com.mkaszynski.tdd.pricing;

import java.util.ArrayList;
import java.util.List;

class Pricing {

    private final Promotion promotion;
    private final EventSender eventSender;

    Pricing(Promotion promotion, EventSender eventSender) {
        this.promotion = promotion;
        this.eventSender = eventSender;
    }

    List<Product> reprice(List<Product> input) {
        List<Product> products = new ArrayList<>();
        for (Product product : input) {
            if (product.isFood()) {
                products.addAll(promotion.apply(product));
            } else {
                products.add(product);
            }
        }
        eventSender.send(new RepricedProductsEvent(products));
        return products;
    }

    String getReceipt(List<Product> products) {
        StringBuilder result = new StringBuilder();
        for (Product product : products) {
            result.append(product.getName()).append(" ").append(product.getPrice()).append(" zł");
        }
        eventSender.send(new ReceiptGeneratedEvent());
        return result.toString();
    }

}
