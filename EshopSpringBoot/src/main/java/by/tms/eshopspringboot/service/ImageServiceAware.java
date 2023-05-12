package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.entity.Image;

public interface ImageServiceAware {
    int saveImage(Image image);

    Image findById(int id) throws Exception;
}
