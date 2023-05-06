package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.service.ImageServiceAware;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
    public void getImage(@PathVariable int imageId, HttpServletResponse response) {
        byte[] image = imageService.getImageById(imageId);
        response.setContentType(imageService.getImageContentTypeById(imageId));
        response.setContentLength(image.length);

        try {
            response.getOutputStream().write(image);
        } catch (IOException e) {
            log.error("Error, while trying to write stream in response", e);
        }
    }
}
