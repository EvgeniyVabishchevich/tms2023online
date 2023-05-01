package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.model.User;
import by.tms.eshopspringboot.model.enums.RequestParamsConstants;
import by.tms.eshopspringboot.model.enums.UserType;
import by.tms.eshopspringboot.service.UserServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.regex.Pattern;

import static by.tms.eshopspringboot.model.enums.Page.LOGIN;
import static by.tms.eshopspringboot.model.enums.Page.REGISTER;
import static by.tms.eshopspringboot.model.enums.RequestParamsConstants.BIRTHDAY;
import static by.tms.eshopspringboot.model.enums.RequestParamsConstants.EMAIL;
import static by.tms.eshopspringboot.model.enums.RequestParamsConstants.NAME;
import static by.tms.eshopspringboot.model.enums.RequestParamsConstants.PASSWORD;
import static by.tms.eshopspringboot.model.enums.RequestParamsConstants.PASSWORD_REPEAT;
import static by.tms.eshopspringboot.model.enums.RequestParamsConstants.SURNAME;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserServiceAware userService;
    private static final LocalDate birthdayBorder = LocalDate.of(1907, 3, 4);

    @GetMapping
    public ModelAndView registerPage() {
        return new ModelAndView(REGISTER.getValue());
    }

    @PostMapping
    public ModelAndView register(@RequestParam(RequestParamsConstants.LOGIN) String login, @RequestParam(NAME) String name,
                                 @RequestParam(SURNAME) String surname, @RequestParam(EMAIL) String email,
                                 @RequestParam(PASSWORD) String password, @RequestParam(PASSWORD_REPEAT) String passwordRepeat,
                                 @RequestParam(BIRTHDAY) String birthday) {
        ModelAndView modelAndView = new ModelAndView();

        if (isLoginValid(login, userService) && isNameValid(name) && isNameValid(surname) && isEmailValid(email) &&
                password.equals(passwordRepeat) && isBirthdayValid(birthday)) {
            User newUser = new User(UserType.CLIENT, login, name, surname, email, LocalDate.parse(birthday));
            userService.addUser(newUser, password);

            modelAndView.setViewName(LOGIN.getValue());
        } else {
            modelAndView.addObject("mistake", "Wrong parameters!!!");
            modelAndView.setViewName(REGISTER.getValue());
        }
        return modelAndView;
    }

    public boolean isBirthdayValid(String birthday) {
        return birthday != null && !birthday.isEmpty() && LocalDate.parse(birthday).isAfter(birthdayBorder);
    }

    public boolean isEmailValid(String email) {
        return !email.isEmpty() && Pattern.compile("^(.+)@(\\S+)").matcher(email).matches();
    }

    public boolean isNameValid(String name) {
        return !name.isEmpty() && Pattern.compile("[A-Z][a-z]*").matcher(name).matches();
    }

    public boolean isLoginValid(String login, UserServiceAware userService) {
        return !login.isEmpty() && !userService.loginInUse(login);
    }
}
