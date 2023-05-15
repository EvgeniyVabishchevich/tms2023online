package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.entity.Image;
import by.tms.eshopspringboot.service.ImageServiceAware;
import exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/db/images")
public class ImageController {

    private final ImageServiceAware imageService;

    @GetMapping("/{imageId}")
    public void getImage(@PathVariable int imageId, HttpServletResponse response) throws NotFoundException {
        Image image = imageService.findById(imageId);
        response.setContentType(image.getContentType());
        response.setContentLength(image.getImage().length);

        try {
            response.getOutputStream().write(image.getImage());
        } catch (IOException e) {
            log.error("Error, while trying to write stream in response", e);
        }
    }

    @ExceptionHandler(NotFoundException.class)
    private void handleError(HttpServletRequest request, NotFoundException exception) {
        log.error("Request " + request + " raised" + exception);
    }
}
