package by.tms.eshopspringboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Map;

import static by.tms.eshopspringboot.utils.Constants.RequestParameters.PRODUCT_ID;
import static by.tms.eshopspringboot.utils.Constants.SessionAttributes.SHOPPING_CART_MAP;

@RestController
@RequestMapping("/add-product")
public class AddProductCartController {
    @PostMapping
    public void addProduct(@RequestParam(PRODUCT_ID) Long productId, @SessionAttribute(SHOPPING_CART_MAP) Map<Long, Integer> shoppingCartMap) {
        shoppingCartMap.merge(productId, 1, Integer::sum);
    }
}
