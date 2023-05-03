package by.tms.eshopspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Map;

import static by.tms.eshopspringboot.utils.Constants.RequestParameters.PRODUCT_ID;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequiredArgsConstructor
@RequestMapping("/remove")
public class RemoveFromCartController {
    @PostMapping
    @ResponseStatus(value = OK)
    public void removeProductFromCart(@SessionAttribute Map<Integer, Integer> cartProductsMap, @RequestParam(PRODUCT_ID) int productId) {
        if (cartProductsMap.get(productId) > 1) {
            cartProductsMap.compute(productId, (key, value) -> value - 1);
        } else {
            cartProductsMap.remove(productId);
        }
    }
}
