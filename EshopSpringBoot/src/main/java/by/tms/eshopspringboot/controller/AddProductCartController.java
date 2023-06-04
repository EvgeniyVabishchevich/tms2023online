package by.tms.eshopspringboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Map;

import static by.tms.eshopspringboot.utils.Constants.RequestParameters.PRODUCT_ID;

@RestController
@RequestMapping("/add-product")
public class AddProductCartController {
    @PostMapping
    public void addProduct(@RequestParam(PRODUCT_ID) Long productId, @SessionAttribute("cartProductsMap") Map<Long, Integer> cartProductsMap) {
        cartProductsMap.merge(productId, 1, Integer::sum);
    }
}
