package exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import static by.tms.eshopspringboot.utils.Constants.Attributes.ERROR_MESSAGE;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.ERROR;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.ERROR404;

@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    private ModelAndView handleError(HttpServletRequest request, NotFoundException exception) {
        log.error("Request " + request + " raised" + exception);

        ModelAndView modelAndView = new ModelAndView(ERROR404);
        modelAndView.addObject(ERROR_MESSAGE, exception.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    private ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        log.error("Request" + request + " raised" + e);
        ModelAndView modelAndView = new ModelAndView(ERROR);
        modelAndView.addObject(ERROR_MESSAGE, e.getMessage());
        return modelAndView;
    }
}
