package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.exception.NotFoundException;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import by.tms.eshopspringboot.service.UserServiceAware;
import by.tms.eshopspringboot.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.MDC;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static by.tms.eshopspringboot.utils.Constants.Attributes.ERROR_MESSAGE;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.CATEGORIES_PATH;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.LOGIN;
import static by.tms.eshopspringboot.utils.Constants.SessionAttributes.SHOPPING_CART_MAP;
import static by.tms.eshopspringboot.utils.Constants.SessionAttributes.USER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@SessionAttributes({SHOPPING_CART_MAP, USER})
public class SignInController {
    private final UserServiceAware userService;
    private final CategoryServiceAware categoryService;

    @GetMapping
    public ModelAndView showLoginPage(@RequestParam(required = false) String error) {
        ModelAndView modelAndView = new ModelAndView();

        if (error != null) {
            modelAndView.addObject(ERROR_MESSAGE, "Wrong login or password");
        }

        modelAndView.setViewName(LOGIN);

        return modelAndView;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/success")
    public ModelAndView logIn(@RequestParam String login) throws NotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        MDC.put("userId", userService.findByLogin(login).getId());
        modelAndView.addObject(SHOPPING_CART_MAP, new HashMap<Long, Integer>());
        modelAndView.addObject(USER, userService.findByLogin(login));

        modelAndView.addObject("categories", categoryService.getCategories());
        modelAndView.setViewName(CATEGORIES_PATH);

        return modelAndView;
    }
}
