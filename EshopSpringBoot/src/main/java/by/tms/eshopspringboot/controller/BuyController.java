package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.entity.Order;
import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.entity.User;
import by.tms.eshopspringboot.exception.NotFoundException;
import by.tms.eshopspringboot.service.OrderServiceAware;
import by.tms.eshopspringboot.service.ProductServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static by.tms.eshopspringboot.utils.Constants.MappingPath.BUY;

@RestController
@RequestMapping("/buy")
@RequiredArgsConstructor
public class BuyController {
    private final OrderServiceAware orderService;

    private final ProductServiceAware productService;

    @GetMapping
    public ModelAndView buy(@SessionAttribute("cartProductsMap") Map<Long, Integer> cartProductsMap,
                            @SessionAttribute("user") User user) throws NotFoundException {
        Map<Product, Integer> products = new HashMap<>();

        for (Map.Entry<Long, Integer> entry : cartProductsMap.entrySet()) {
            products.put(productService.findById(entry.getKey()), entry.getValue());
        }

        Order order = new Order(LocalDate.now(), products, user.getId());

        orderService.addOrder(order);

        cartProductsMap.clear();

        return new ModelAndView(BUY);
    }
}
