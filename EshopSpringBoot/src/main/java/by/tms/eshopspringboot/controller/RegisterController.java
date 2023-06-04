package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.dto.UserDTO;
import by.tms.eshopspringboot.enums.Role;
import by.tms.eshopspringboot.service.UserServiceAware;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.EnumSet;

import static by.tms.eshopspringboot.utils.Constants.MappingPath.REGISTER;
import static by.tms.eshopspringboot.utils.Constants.RequestParameters.PASSWORD_REPEAT;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserServiceAware userService;

    @GetMapping
    public ModelAndView registerPage() {
        ModelAndView modelAndView = new ModelAndView(REGISTER);
        modelAndView.addObject("newUser", new UserDTO());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView register(@Valid @ModelAttribute("newUser") UserDTO newUser, BindingResult bindingResult,
                                 @RequestParam(PASSWORD_REPEAT) String passwordRepeat) {
        ModelAndView modelAndView = new ModelAndView();

        if (!newUser.getPassword().equals(passwordRepeat)) {
            bindingResult.rejectValue("password", "400", "Entered passwords not equal.");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(REGISTER);
            return modelAndView;
        } else {
            newUser.setRoles(EnumSet.of(Role.USER));
            userService.addUser(newUser);
            return new ModelAndView("forward:/login");
        }
    }
}
