package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.model.Product;
import by.tms.eshopspringboot.model.enums.RequestParamsConstants;
import by.tms.eshopspringboot.service.ProductServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static by.tms.eshopspringboot.model.enums.Page.CART;


@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final ProductServiceAware productService;

    @GetMapping
    public ModelAndView showCart(@SessionAttribute("cartProductsMap") HashMap<Integer, Integer> cartProductsMap) {
        ModelAndView modelAndView = new ModelAndView(CART.getValue());

        Map<Product, Integer> productsMap = productService.getProductsByIds(cartProductsMap);
        BigDecimal totalPrice = productsMap.keySet().stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(productsMap.get(product)))).reduce(BigDecimal.ZERO, BigDecimal::add);

        modelAndView.addObject(RequestParamsConstants.PRODUCTS_MAP, productsMap);
        modelAndView.addObject("totalPrice", totalPrice);

        return modelAndView;
    }
}