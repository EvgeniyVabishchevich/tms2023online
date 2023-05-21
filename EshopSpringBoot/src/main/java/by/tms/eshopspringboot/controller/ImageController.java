package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.entity.Image;
import by.tms.eshopspringboot.exception.NotFoundException;
import by.tms.eshopspringboot.service.ImageServiceAware;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/db/images")
public class ImageController {

    private final ImageServiceAware imageService;

    @GetMapping("/{imageId}")
    public void getImage(@PathVariable int imageId, HttpServletResponse response) {
        try {
            Image image = imageService.findById(imageId);
            response.setContentType(image.getContentType());
            response.setContentLength(image.getImage().length);

            response.getOutputStream().write(image.getImage());
        } catch (IOException | NotFoundException e) {
            log.error("Some error occurred, while loading the image", e);
        }
    }
}
