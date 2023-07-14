package by.tms.eshopspringboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static by.tms.eshopspringboot.utils.Constants.Attributes.ERROR_MESSAGE;
import static by.tms.eshopspringboot.utils.Constants.Attributes.HTTP_STATUS;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.ERROR;

@RestController
@RequestMapping("/access-denied")
public class AccessDeniedController {
    @GetMapping
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView showPage() {
        ModelAndView modelAndView = new ModelAndView(ERROR);
        modelAndView.addObject(ERROR_MESSAGE, "Access denied");
        modelAndView.addObject(HTTP_STATUS, HttpStatus.FORBIDDEN.value());

        return modelAndView;
    }
}
