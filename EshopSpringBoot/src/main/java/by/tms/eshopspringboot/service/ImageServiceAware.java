package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.entity.Image;
import by.tms.eshopspringboot.exception.NotFoundException;

public interface ImageServiceAware {
    Long saveImage(Image image);

    Image findById(Long id) throws NotFoundException;
}
