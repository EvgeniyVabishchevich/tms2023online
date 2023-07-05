package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.entity.Image;
import by.tms.eshopspringboot.exception.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageServiceAware {
    Long saveImage(MultipartFile image) throws IOException;

    Image findById(Long id) throws NotFoundException;
}
