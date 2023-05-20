package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.entity.Image;
import by.tms.eshopspringboot.exception.NotFoundException;

public interface ImageServiceAware {
    int saveImage(Image image);

    Image findById(int id) throws NotFoundException;
}
