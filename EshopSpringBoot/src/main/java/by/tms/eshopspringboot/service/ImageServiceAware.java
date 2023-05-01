package by.tms.eshopspringboot.service;

import java.io.InputStream;

public interface ImageServiceAware {
    void addImage(String imageName, byte[] imageBytes);

    byte[] getImageByName(String name);
}
