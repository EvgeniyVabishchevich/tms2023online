package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.entity.Order;
import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.entity.User;
import by.tms.eshopspringboot.service.OrderServiceAware;
import by.tms.eshopspringboot.service.ProductServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static by.tms.eshopspringboot.utils.Constants.MappingPath.BUY;
import static by.tms.eshopspringboot.utils.ThrowingConsumer.throwingConsumerWrapper;

@Controller
@RequestMapping("/buy")
@RequiredArgsConstructor
public class BuyController {
    private final OrderServiceAware orderService;

    private final ProductServiceAware productService;

    @GetMapping
    public ModelAndView buy(@SessionAttribute("cartProductsMap") Map<Integer, Integer> cartProductsMap,
                            @SessionAttribute("user") User user) {
        Map<Product, Integer> products = new HashMap<>();

        cartProductsMap.keySet().forEach(throwingConsumerWrapper(id -> products.put(productService.findById(id), cartProductsMap.get(id))));

        Order order = new Order(LocalDate.now(), products, user.getId());

        orderService.addOrder(order);

        cartProductsMap.clear();

        return new ModelAndView(BUY);
    }
}
