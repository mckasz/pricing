package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.dto.AddProductCommand;
import com.mkaszynski.tdd.pricing.dto.BasketSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BasketResource {

    @Autowired
    private BasketService basketService;

    @GetMapping("/basket/{id}")
    public BasketSummary readBasketById(@PathVariable Long id) {
        return basketService.summary(id);
    }

    @PostMapping("/basket/")
    public Long putItemIntoBasket(@RequestBody AddProductCommand command) {
        return basketService.add(command);
    }
}
