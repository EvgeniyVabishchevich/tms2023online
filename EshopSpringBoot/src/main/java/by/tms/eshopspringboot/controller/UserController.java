package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.model.User;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import by.tms.eshopspringboot.service.OrderServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import static by.tms.eshopspringboot.model.enums.Page.USER;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@SessionAttributes("user")
public class UserController {
    private final OrderServiceAware orderService;
    private final CategoryServiceAware categoryService;

    @GetMapping
    public ModelAndView execute(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView(USER.getValue());
        modelAndView.addObject("orders", orderService.getOrdersByUserId(user.getId()));
        modelAndView.addObject("categories", categoryService.getCategories());
        return modelAndView;
    }
}
