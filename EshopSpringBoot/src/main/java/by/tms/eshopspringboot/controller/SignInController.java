package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.model.User;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import by.tms.eshopspringboot.service.UserServiceAware;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.UUID;

import static by.tms.eshopspringboot.utils.Constants.MappingPath.CATEGORIES;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.LOGIN;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
@SessionAttributes({"cartProductsMap", "user"})
public class SignInController {
    private final UserServiceAware userService;
    private final CategoryServiceAware categoryService;

    @GetMapping
    public ModelAndView goToLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("unknownUser", new User());
        modelAndView.setViewName(LOGIN);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView logIn(@Valid @ModelAttribute("unknownUser") User newUser, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasFieldErrors("login") || bindingResult.hasFieldErrors("password")) {
            modelAndView.setViewName(LOGIN);
            return modelAndView;
        }

        if (userService.existsByLoginAndPassword(newUser.getLogin(), newUser.getPassword())) {
            ThreadContext.put("conversationId", UUID.randomUUID().toString());
            modelAndView.addObject("cartProductsMap", new HashMap<Integer, Integer>());
            modelAndView.addObject("user", userService.findByLogin(newUser.getLogin()));

            modelAndView.addObject("categories", categoryService.getCategories());
            modelAndView.setViewName(CATEGORIES);
        } else {
            modelAndView.setViewName(LOGIN);
        }
        return modelAndView;
    }
}
