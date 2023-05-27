package by.tms.eshopspringboot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import static by.tms.eshopspringboot.utils.Constants.Attributes.ERROR_MESSAGE;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.ERROR;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.ERROR404;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    private ModelAndView handleError(NotFoundException e) {
        log.error("Unexpected exception", e);

        ModelAndView modelAndView = new ModelAndView(ERROR404);
        modelAndView.addObject(ERROR_MESSAGE, e.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @Order(Ordered.LOWEST_PRECEDENCE)
    private ModelAndView defaultErrorHandler(Exception e) {
        log.error("Unexpected exception.", e);

        ModelAndView modelAndView = new ModelAndView(ERROR);
        modelAndView.addObject(ERROR_MESSAGE, e.getMessage());

        return modelAndView;
    }
}
