package by.tms.eshopspringboot.repository;

import java.io.InputStream;

public interface ImageRepository {
    void addImage(String imageName, byte[] imageBytes);

    byte[] getImageByName(String name);
}
