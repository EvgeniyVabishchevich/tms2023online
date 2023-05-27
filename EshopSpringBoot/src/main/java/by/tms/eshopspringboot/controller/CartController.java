package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.exception.NotFoundException;
import by.tms.eshopspringboot.service.ProductServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Map;

import static by.tms.eshopspringboot.utils.Constants.Attributes.PRODUCTS_MAP;
import static by.tms.eshopspringboot.utils.Constants.Attributes.TOTAL_PRICE;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.CART;
import static by.tms.eshopspringboot.utils.Constants.RequestParameters.PRODUCT_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final ProductServiceAware productService;

    @GetMapping
    public ModelAndView showCart(@SessionAttribute("cartProductsMap") Map<Integer, Integer> cartProductsMap) throws NotFoundException {
        ModelAndView modelAndView = new ModelAndView(CART);

        Map<Product, Integer> productsMap = productService.getProductsByIds(cartProductsMap);
        BigDecimal totalPrice = productsMap.keySet().stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(productsMap.get(product)))).reduce(BigDecimal.ZERO, BigDecimal::add);

        modelAndView.addObject(PRODUCTS_MAP, productsMap);
        modelAndView.addObject(TOTAL_PRICE, totalPrice);

        return modelAndView;
    }

    @DeleteMapping
    public void removeProductFromCart(@SessionAttribute Map<Integer, Integer> cartProductsMap, @RequestParam(PRODUCT_ID) int productId) {
        if (cartProductsMap.get(productId) > 1) {
            cartProductsMap.compute(productId, (key, value) -> value - 1);
        } else {
            cartProductsMap.remove(productId);
        }
    }
}
