package by.tms.eshopspringboot.interceptor;

import by.tms.eshopspringboot.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jboss.logging.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");

        if (userDTO != null) {
            MDC.put("userId", ((UserDTO) request.getSession().getAttribute("user")).getId());
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.clear();

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
