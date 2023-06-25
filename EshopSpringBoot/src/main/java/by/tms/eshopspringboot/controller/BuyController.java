package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.dto.UserDTO;
import by.tms.eshopspringboot.entity.Order;
import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.exception.NotFoundException;
import by.tms.eshopspringboot.service.OrderServiceAware;
import by.tms.eshopspringboot.service.ProductServiceAware;
import by.tms.eshopspringboot.utils.Constants;
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
import static by.tms.eshopspringboot.utils.Constants.SessionAttributes.SHOPPING_CART_MAP;

@RestController
@RequestMapping("/buy")
@RequiredArgsConstructor
public class BuyController {
    private final OrderServiceAware orderService;

    private final ProductServiceAware productService;

    @GetMapping
    public ModelAndView buy(@SessionAttribute(SHOPPING_CART_MAP) Map<Long, Integer> shoppingCartMap,
                            @SessionAttribute("user") UserDTO userDTO) throws NotFoundException {
        Map<Product, Integer> products = new HashMap<>();

        for (Map.Entry<Long, Integer> entry : shoppingCartMap.entrySet()) {
            products.put(productService.findById(entry.getKey()), entry.getValue());
        }

        Order order = new Order(LocalDate.now(), products, userDTO.getId());

        orderService.addOrder(order);

        shoppingCartMap.clear();

        return new ModelAndView(BUY);
    }
}
