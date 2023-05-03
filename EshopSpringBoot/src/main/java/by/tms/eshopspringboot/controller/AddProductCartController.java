package by.tms.eshopspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashMap;
import java.util.Map;

import static by.tms.eshopspringboot.model.enums.RequestParamsConstants.PRODUCT_ID;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/add-product")
public class AddProductCartController {
    @PostMapping
    @ResponseStatus(value = OK)
    public void addProduct(@RequestParam(PRODUCT_ID) int productId, @SessionAttribute("cartProductsMap") Map<Integer, Integer> cartProductsMap) {
        cartProductsMap.merge(productId, 1, Integer::sum);
    }
}
